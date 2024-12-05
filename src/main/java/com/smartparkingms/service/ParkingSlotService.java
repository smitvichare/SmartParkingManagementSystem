package com.smartparkingms.service;

import com.smartparkingms.dto.ParkingReqDTO;
import com.smartparkingms.dto.ParkingRespDTO;

import java.util.List;

public interface ParkingSlotService {
    ParkingRespDTO addSpot(ParkingReqDTO parkingReqDTO);

    ParkingRespDTO getSpot(long id);

    List<ParkingRespDTO> getAllSpots();

    void delSpot(long id);

    ParkingRespDTO updateSpot(long id, ParkingReqDTO parkingReqDTO);

    ParkingRespDTO releaseSlot(long id);

    List<ParkingRespDTO> filterSlots(String vType);


}
