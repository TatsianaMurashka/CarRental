package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Car creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CarCreateRequest {

    @NotNull
    @Size(min = 6, max = 255)
    @ApiModelProperty(dataType = "string", notes = "registration number")
    private String registrationNumber;

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "car id")
    private Long modelId;

    @Positive
    private Double pricePerDay;

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "office id")
    private Long officeId;
}
