package com.smartparkingms.dto;

import com.smartparkingms.model.Reservation;
import com.smartparkingms.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDTO {

    String name;
    String email;
    long phone;
    List<String> registeredVehicles;

    public UserRespDTO(User user){
        this.name=user.getName();
        this.phone= user.getPhone();
        this.email= user.getEmail();
        this.registeredVehicles=user.getRegisteredVehicles();
    }
}
