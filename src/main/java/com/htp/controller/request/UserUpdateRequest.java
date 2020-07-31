package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(description = "User update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserUpdateRequest extends UserCreateRequest {
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(dataType = "string", notes = "user first name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(dataType = "string", notes = "User last name")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^(\\d{10}|\\d{12})$")
    @ApiModelProperty(dataType = "string", notes = "User phone number name")
    private String phoneNumber;

    @NotNull
    @Size(min = 6, max = 255)
    @ApiModelProperty(dataType = "string", notes = "User login")
    private String login;

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "user id")
    private Long id;

}
