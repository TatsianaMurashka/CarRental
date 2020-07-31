package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(description = "Rent update model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RentUpdateRequest extends RentCreateRequest{
    private Long userId;

    private Long carId;

    private LocalDateTime rentStartDate;

    private LocalDateTime rentEndDate;

    private Double rentPrice;

    private Long id;
}
