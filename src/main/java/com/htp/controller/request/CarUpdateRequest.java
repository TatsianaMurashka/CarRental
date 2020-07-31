package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(description = "Car update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CarUpdateRequest extends CarCreateRequest {

    private String registrationNumber;

    private Long modelId;

    private Double pricePerDay;

    private Long officeId;

    private Long id;
}
