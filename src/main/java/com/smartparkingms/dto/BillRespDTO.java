package com.smartparkingms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillRespDTO {

    private long reservationId;
    private double amount;
    private String paymentStatus;
}
