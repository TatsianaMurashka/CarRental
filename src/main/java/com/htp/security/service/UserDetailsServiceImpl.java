package com.htp.security.service;

import com.htp.dao.RoleDao;
import com.htp.dao.UserDao;
import com.htp.domain.Role;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userRepository;

    private RoleDao roleRepository;

    public UserDetailsServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userRepository,
                                  RoleDao roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> searchResult = userRepository.findByLogin(username);

        if (searchResult.isPresent()) {

            User user = searchResult.get();
            List<Role> userAuthorities = roleRepository.findByUserId(user.getId());
            String collectedUserAuthorities = userAuthorities.stream().map(Role::getRoleName).collect(Collectors.joining(","));
            return  new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(collectedUserAuthorities)
            );
        } else {
            throw new UsernameNotFoundException("User with login " + username + " was not found");
        }
    }
}
