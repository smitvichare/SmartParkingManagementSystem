package com.smartparkingms.service;

import com.smartparkingms.dto.ReservReqDTO;
import com.smartparkingms.dto.ReservRespDTO;
import com.smartparkingms.exception.IDNotFoundException;
import com.smartparkingms.exception.PlateNotFoundException;
import com.smartparkingms.model.Reservation;
import com.smartparkingms.model.User;
import com.smartparkingms.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    public ReservationRepo reservationRepo;

    @Autowired
    public BillServiceImpl billService;

    @Autowired
    public ParkingSlotServiceImpl parkingSlotService;

    @Autowired
    public UserServiceImpl userService;

    @Override
    public ReservRespDTO addReservation(ReservReqDTO reservReqDTO) {
        Reservation reservation=new Reservation(reservReqDTO);
        User user=userService.getUser1(reservation.getUserId());
        reservation.setUser(user);
        String r = reservation.getVehicleNumber();
        String plate=user.getRegisteredVehicles().stream().filter(p-> Objects.equals(p, r)).findFirst().orElseThrow(()-> new PlateNotFoundException("Plates are not registered!"));
        long parkId=parkingSlotService.giveParking(reservReqDTO.getVehicleType());
        reservation.setSlotId(parkId);
        billService.createBill(reservation);

        return MapToDTO(reservationRepo.save(reservation));
    }


    @Override
    public ReservRespDTO getReservation(long id) {
        return MapToDTO(reservationRepo.findById(id).orElseThrow(()->new IDNotFoundException("ID not Found")));
    }

    @Override
    public List<ReservRespDTO> getAllReservation() {
        List<Reservation> reservations=reservationRepo.findAll();
        return reservations.stream().map(this::MapToDTO).collect(Collectors.toList());
    }

    @Override
    public void delReservation(long id) {
        getReservation(id);
        reservationRepo.deleteById(id);
    }

    @Override
    public ReservRespDTO updateReservation(long id, ReservReqDTO reservReqDTO) {
        Reservation reservation=new Reservation();
        reservation=reservationRepo.findById(id).orElseThrow(()->new IDNotFoundException("ID not Found"));
        reservation.setStatus(reservReqDTO.getStatus());
        reservation.setEndTime(reservReqDTO.getEndTime());
        reservation.setStartTime(reservReqDTO.getStartTime());
        reservation.setUserId(reservReqDTO.getUserId());
        reservation.setVehicleType(reservReqDTO.getVehicleType());
        return MapToDTO(reservationRepo.save(reservation));
    }

    @Override
    public ReservRespDTO cancelReservation(long id) {
        Reservation reservation=reservationRepo.findById(id).orElseThrow(()-> new IDNotFoundException("ID not found"));
        reservation.setStatus("Cancelled");
        return MapToDTO(reservationRepo.save(reservation));
    }

    @Override
    public List<ReservRespDTO> history(long id) {
        List<ReservRespDTO> reservations=reservationRepo.findAll().stream().filter(r->r.getUserId()==id).map(reservation -> MapToDTO(reservation)).collect(Collectors.toList());
        return reservations;
    }


    private ReservRespDTO MapToDTO(Reservation reservation) {

        ReservRespDTO reservRespDTO=new ReservRespDTO();
        reservRespDTO.setId(reservation.getId());
        reservRespDTO.setSlotId(reservation.getSlotId());
        reservRespDTO.setStatus(reservation.getStatus());
        reservRespDTO.setVehicleNumber(reservation.getVehicleNumber());
        reservRespDTO.setStartTime(reservation.getStartTime());
        reservRespDTO.setEndTime(reservation.getEndTime());
        reservRespDTO.setUserId(reservation.getUserId());
        reservRespDTO.setVehicleType(reservation.getVehicleType());
        reservRespDTO.setUser(userService.mapToDTO(reservation.getUser()));
        return reservRespDTO;
    }


}
