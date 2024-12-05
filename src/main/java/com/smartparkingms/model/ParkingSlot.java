package com.smartparkingms.model;

import com.smartparkingms.dto.ParkingReqDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParkingSlot {
    @Id
    @GeneratedValue
    private long id;
    private long slotNumber;
    private long level;
    private boolean available;
    private String vehicleType;

    public ParkingSlot(ParkingReqDTO parkingReqDTO){
        this.slotNumber=parkingReqDTO.getSlotNumber();
        this.level= parkingReqDTO.getLevel();
        this.available= parkingReqDTO.isAvailable();
        this.vehicleType=parkingReqDTO.getVehicleType();

    }

}
