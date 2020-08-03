package com.htp.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel(description = "Authentication object with login and password")
public class AuthRequest implements Serializable {

    @NotEmpty
    @ApiModelProperty(required = true, allowableValues = "Admin123", dataType = "string")
    private String username;

    @NotEmpty
    @ApiModelProperty(required = true, allowableValues = "Admin123", dataType = "string")
    private String password;
}
