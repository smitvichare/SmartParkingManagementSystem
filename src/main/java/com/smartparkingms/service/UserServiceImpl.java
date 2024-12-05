package com.smartparkingms.service;

import com.smartparkingms.dto.UserReqDTO;
import com.smartparkingms.dto.UserRespDTO;
import com.smartparkingms.model.Reservation;
import com.smartparkingms.model.User;
import com.smartparkingms.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    private UserRepo userRepo;
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    @Override
    public UserRespDTO addUser(UserReqDTO userReqDTO) {
        User user=new User(userReqDTO);
        return mapToDTO(userRepo.save(user));
    }

    private UserRespDTO mapToDTO(User user) {
        UserRespDTO userRespDTO=new UserRespDTO();
        userRespDTO.setName(user.getName());
        userRespDTO.setEmail(user.getEmail());
        userRespDTO.setPhone(user.getPhone());
        userRespDTO.setRegisteredVehicles(user.getRegisteredVehicles());
        return userRespDTO;
    }

    @Override
    public UserRespDTO getUser(long id) {
        return mapToDTO(userRepo.findById(id).orElseThrow(()->new RuntimeException("ID not found")));
    }

    @Override
    public void deleteUser(long id) {
        getUser(id);
        userRepo.deleteById(id);
    }

    @Override
    public UserRespDTO updateUser(long id,UserReqDTO userReqDTO) {

        getUser(id);
        User user1=userRepo.findById(id).orElseThrow(()->new RuntimeException("ID not found"));
        user1.setName(userReqDTO.getName());
        user1.setEmail(userReqDTO.getEmail());
        user1.setPhone(userReqDTO.getPhone());
        if (userReqDTO.getRegisteredVehicles() != null) {
            user1.getRegisteredVehicles().clear();
            user1.getRegisteredVehicles().addAll(userReqDTO.getRegisteredVehicles());
        }
        return mapToDTO(userRepo.save(user1));
    }

    @Override
    public List<UserRespDTO> getAllUsers() {
        List<User> users=userRepo.findAll();

        return users.stream().map(u->mapToDTO(u)).collect(Collectors.toList());
    }
}
