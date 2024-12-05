package com.smartparkingms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingReqDTO {

    long slotNumber;
    long level;
    boolean available;
    String vehicleType;
}
