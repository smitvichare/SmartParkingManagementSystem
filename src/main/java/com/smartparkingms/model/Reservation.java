package com.smartparkingms.model;

import com.smartparkingms.dto.ReservReqDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    private long id;
    private long userId;
    private long slotId;
    private String vehicleNumber;
    private String vehicleType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;



    public Reservation(ReservReqDTO reservReqDTO){

        this.id=reservReqDTO.getId();
        this.vehicleType=reservReqDTO.getVehicleType();
        this.userId=reservReqDTO.getUserId();
        this.vehicleNumber=reservReqDTO.getVehicleNumber();
        this.startTime=reservReqDTO.getStartTime();
        this.endTime=reservReqDTO.getEndTime();
        this.status=reservReqDTO.getStatus();
    }
}
