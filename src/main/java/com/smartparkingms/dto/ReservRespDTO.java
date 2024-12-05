package com.smartparkingms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservRespDTO {

    private long id;
    private long slotId;
    private long userId;
    private String vehicleNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private String vehicleType;
}
