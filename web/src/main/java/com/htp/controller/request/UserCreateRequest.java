package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "User creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserCreateRequest {

    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true, dataType = "string", notes = "User first name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true, dataType = "string", notes = "User last name")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^(\\d{10}|\\d{12})$")
    @ApiModelProperty(required = true, dataType = "string", notes = "User phone number")
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @ApiModelProperty(dataType = "string", notes = "User passport number")
    private String passportData;

    @NotNull
    @Size(min = 6, max = 255)
    @ApiModelProperty(required = true, dataType = "string", notes = "User login")
    private String login;

    @NotNull
    @Size(min = 3, max = 255)
    @ApiModelProperty(required = true, dataType = "string", notes = "User password")
    private String password;
}