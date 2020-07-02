package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@ApiModel(description = "User update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserUpdateRequest {
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
