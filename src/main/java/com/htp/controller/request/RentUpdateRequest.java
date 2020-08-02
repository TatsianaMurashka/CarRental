package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@ApiModel(description = "Rent update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RentUpdateRequest {
    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "user id")
    private Long userId;

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "car id")
    private Long carId;

    private LocalDateTime rentStartDate;

    private LocalDateTime rentEndDate;

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "rent id")
    private Long id;
}
