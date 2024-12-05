package com.smartparkingms.service;

import com.smartparkingms.dto.ReservReqDTO;
import com.smartparkingms.dto.ReservRespDTO;
import com.smartparkingms.exception.IDNotFoundException;
import com.smartparkingms.model.Bill;
import com.smartparkingms.model.ParkingSlot;
import com.smartparkingms.model.Reservation;
import com.smartparkingms.repository.BillRepo;
import com.smartparkingms.repository.ParkingSlotRepo;
import com.smartparkingms.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    public ReservationRepo reservationRepo;

    @Autowired
    public ParkingSlotRepo parkingSlotRepo;

    @Autowired
    public BillRepo billRepo;

    @Autowired
    public ParkingSlotServiceImpl parkingSlotService;

    @Override
    public ReservRespDTO addReservation(ReservReqDTO reservReqDTO) {
        Reservation reservation=new Reservation(reservReqDTO);
        long parkId=giveParking(reservReqDTO.getVehicleType());
        reservation.setSlotId(parkId);
        long duration = ChronoUnit.HOURS.between(reservation.getStartTime(), reservation.getEndTime());
        int rate;
        if(Objects.equals(reservation.getVehicleType(), "bike")){
            rate=1;
        } else if (Objects.equals(reservation.getVehicleType(), "car")) {
            rate=3;
        }
        else if (Objects.equals(reservation.getVehicleType(), "truck")){
            rate=5;
        }
        else {
            throw new IDNotFoundException("Wrong Vehicle Type");
        }
        double amount=rate*duration;
        Bill bill=new Bill();
        bill.setReservationId(reservation.getId());
        bill.setAmount(amount);
        bill.setPaymentStatus("Unpaid");
        billRepo.save(bill);

        return MapToDTO(reservationRepo.save(reservation));
    }

//    public  double generateBill(long id){
//        Reservation reservation=reservationRepo.findById(id).orElseThrow(()->new IDNotFoundException("ID not found"));
//
//        return amount;
//
//    }
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
        return reservRespDTO;
    }

    public long giveParking(String vehicleType){

        ParkingSlot parkingSlot=parkingSlotRepo.findAll().stream().filter(p-> p.isAvailable() && p.getVehicleType().equals(vehicleType)).findFirst().orElseThrow(()->new IDNotFoundException("No available slots."));
        if(parkingSlot!=null){
            parkingSlot.setAvailable(false);
            parkingSlotRepo.save(parkingSlot);
            return parkingSlot.getSlotNumber();
        }
        return 0;
    }
}
