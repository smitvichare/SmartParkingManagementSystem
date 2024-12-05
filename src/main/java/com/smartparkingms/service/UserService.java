package com.smartparkingms.service;

import com.smartparkingms.dto.UserReqDTO;
import com.smartparkingms.dto.UserRespDTO;
import com.smartparkingms.model.User;

import java.util.List;

public interface UserService {
    UserRespDTO addUser(UserReqDTO userReqDTO);

    UserRespDTO getUser(long id);

    void deleteUser(long id);

    UserRespDTO updateUser(long id,UserReqDTO userReqDTO);

    List<UserRespDTO> getAllUsers();
}
