package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@ApiModel(description = "Location update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LocationUpdateRequest {
    @Pattern(regexp = "(\\bBelarus$)|(\\bRussia&)|(\\bUkraine&)")
    @ApiModelProperty(dataType = "string", notes = "Country")
    private String country;

    @NotNull
    @NotEmpty
    @ApiModelProperty(dataType = "string", notes = "City")
    private String city;

    @NotNull
    @NotEmpty
    @ApiModelProperty(dataType = "string", notes = "Street")
    private String street;

    @NotNull
    @NotEmpty
    @ApiModelProperty(dataType = "string", notes = "House")
    private String house;

    @NotNull
    @NotEmpty
    @ApiModelProperty(dataType = "string", notes = "Apartment")
    private String apartment;

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "Location id")
    private Long id;

}

