package com.smartparkingms.service;

import com.smartparkingms.dto.ParkingReqDTO;
import com.smartparkingms.dto.ParkingRespDTO;
import com.smartparkingms.exception.IDNotFoundException;
import com.smartparkingms.model.ParkingSlot;
import com.smartparkingms.repository.ParkingSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{

    @Autowired
    public ParkingSlotRepo parkingSlotRepo;
    @Override
    public ParkingRespDTO addSpot(ParkingReqDTO parkingReqDTO) {
        ParkingSlot parkingSlot=new ParkingSlot(parkingReqDTO);
        return mapTODTO(parkingSlotRepo.save(parkingSlot));
    }

    @Override
    public ParkingRespDTO getSpot(long id) {
        return mapTODTO(parkingSlotRepo.findById(id).orElseThrow(()->new IDNotFoundException("ID not found")));
    }

    @Override
    public List<ParkingRespDTO> getAllSpots() {
        List<ParkingSlot> parkingSlots=parkingSlotRepo.findAll();
        return parkingSlots.stream().map(this::mapTODTO).collect(Collectors.toList());
    }

    @Override
    public void delSpot(long id) {
        getSpot(id);
        parkingSlotRepo.deleteById(id);
    }

    @Override
    public ParkingRespDTO updateSpot(long id, ParkingReqDTO parkingReqDTO) {
        ParkingSlot parkingSlot=parkingSlotRepo.findById(id).orElseThrow(()->new IDNotFoundException("ID not found"));
        parkingSlot.setSlotNumber(parkingReqDTO.getSlotNumber());
        parkingSlot.setLevel(parkingReqDTO.getLevel());
        parkingSlot.setAvailable(parkingReqDTO.isAvailable());
        parkingSlot.setVehicleType(parkingReqDTO.getVehicleType());
        return mapTODTO(parkingSlotRepo.save(parkingSlot));
    }

    @Override
    public ParkingRespDTO releaseSlot(long id) {
        ParkingSlot parkingSlot=parkingSlotRepo.findById(id).orElseThrow(()->new IDNotFoundException("ID not found"));
        parkingSlot.setAvailable(true);
        return mapTODTO(parkingSlotRepo.save(parkingSlot));
    }

    @Override
    public List<ParkingRespDTO> filterSlots(String vType) {
        List<ParkingRespDTO> parkingRespDTOS=parkingSlotRepo.findAll().stream().filter(p-> Objects.equals(p.getVehicleType(), vType)).map(this::mapTODTO).collect(Collectors.toList());
        return parkingRespDTOS;
    }




    private ParkingRespDTO mapTODTO(ParkingSlot parkingSlot) {
        ParkingRespDTO parkingRespDTO=new ParkingRespDTO();
        parkingRespDTO.setSlotNumber(parkingSlot.getSlotNumber());
        parkingRespDTO.setLevel(parkingSlot.getLevel());
        parkingRespDTO.setAvailable(parkingSlot.isAvailable());
        parkingRespDTO.setVehicleType(parkingSlot.getVehicleType());
        return parkingRespDTO;
    }




}
