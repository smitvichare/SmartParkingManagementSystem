package com.smartparkingms.dto;

import com.smartparkingms.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResp2 {

    long id;
    String name;
    String email;
    long phone;
    List<String> registeredVehicles;
    List<Reservation> reservations;
}
