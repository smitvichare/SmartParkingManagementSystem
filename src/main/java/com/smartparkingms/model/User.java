package com.smartparkingms.model;

import com.smartparkingms.dto.UserReqDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "User_Data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    private long phone;
    @ElementCollection
    @CollectionTable(name = "Vehicle_Data",joinColumns = @JoinColumn(name = "user_id"))
    private List<String> registeredVehicles;

    public User(UserReqDTO userReqDTO){
        this.name= userReqDTO.getName();
        this.email=userReqDTO.getEmail();
        this.phone= userReqDTO.getPhone();
        this.registeredVehicles=userReqDTO.getRegisteredVehicles();
    }

}
