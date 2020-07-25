package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@ApiModel(description = "Rent adding model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RentCreateRequest {

    private Long customerId;

    private Long carId;

    private Date rentStartDate;

    private Date rentEndDate;

    private Double rentPrice;

}
