package com.htp.controller.auth;

import com.htp.controller.request.AuthRequest;
import com.htp.controller.request.UserCreateRequest;
import com.htp.controller.response.AuthResponse;
import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import com.htp.domain.Roles;
import com.htp.domain.User;
import com.htp.security.util.TokenUtils;
import com.htp.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@RestController
public class AuthController {

    private TokenUtils tokenUtils;

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private UserService userService;

    private RoleDao roleDao;

    public AuthController(TokenUtils tokenUtils, AuthenticationManager authenticationManager, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, UserService userService, RoleDao roleDao) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @ApiOperation(value = "Login user by username and password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful login user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return new ResponseEntity<>(
                AuthResponse
                .builder()
                .login(authRequest.getUsername())
                .jwtToken(tokenUtils.generateToken(userDetailsService.loadUserByUsername(authRequest.getUsername())))
                        .build(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("/registration")
    public User create(@Valid @RequestBody UserCreateRequest createRequest) {
        User user = new User();
        user.setFirstName(createRequest.getFirstName());
        user.setLastName(createRequest.getLastName());
        user.setPhoneNumber(createRequest.getPhoneNumber());
        user.setPassportData(createRequest.getPassportData());
        user.setLogin(createRequest.getLogin());
        user.setPassword(createRequest.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));

        User savedUser = userService.save(user);

        Role role = new Role();
        role.setRoleName(Roles.ROLE_USER.name());
        role.setUserId(savedUser.getId());

        Role savedRole = roleDao.save(role);

        savedUser.setRole(savedRole);

        return savedUser;
    }
}