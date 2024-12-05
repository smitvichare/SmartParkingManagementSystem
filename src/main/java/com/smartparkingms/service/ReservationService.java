package com.smartparkingms.service;

import com.smartparkingms.dto.ReservReqDTO;
import com.smartparkingms.dto.ReservRespDTO;

import java.util.List;

public interface ReservationService {
    ReservRespDTO addReservation(ReservReqDTO reservReqDTO);

    ReservRespDTO getReservation(long id);

    List<ReservRespDTO> getAllReservation();

    void delReservation(long id);

    ReservRespDTO updateReservation(long id, ReservReqDTO reservReqDTO);

    ReservRespDTO cancelReservation(long id);

    List<ReservRespDTO> history(long id);
}
