package com.smartparkingms.dto;

import com.smartparkingms.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDTO {
    String name;
    String email;
    long phone;
    //@Pattern(regexp = "^[A-Z]{2}[0-9]{1,2}[A-Z]{1,2}[0-9]{4}$", message = "Invalid number plate")
    List<@Pattern(regexp = "^[A-Z]{2}[0-9]{1,2}[A-Z]{1,2}[0-9]{4}$", message = "Invalid number plate") String> registeredVehicles;

}
