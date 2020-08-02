package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Rent adding model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RentCreateRequest {

    @Positive
    @ApiModelProperty(required = true, dataType = "long", notes = "user id")
    private Long userId;

    private LocalDateTime rentStartDate;

    private LocalDateTime rentEndDate;
}
