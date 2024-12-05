package com.smartparkingms.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservReqDTO {

    private long id;
    private long userId;
    @Pattern(regexp = "^[A-Z]{2}[0-9]{1,2}[A-Z]{1,2}[0-9]{4}$", message = "Invalid number plate")
    private String vehicleNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private String vehicleType;
}
