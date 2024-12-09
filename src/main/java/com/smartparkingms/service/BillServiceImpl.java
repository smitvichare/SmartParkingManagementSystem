package com.smartparkingms.service;

import com.smartparkingms.dto.BillRespDTO;
import com.smartparkingms.exception.IDNotFoundException;
import com.smartparkingms.model.Bill;
import com.smartparkingms.model.Reservation;
import com.smartparkingms.repository.BillRepo;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    BillRepo billRepo;

    @Autowired
    ParkingSlotServiceImpl parkingSlotService;

    @Override
    public BillRespDTO generateBill(long id) {
        Bill bill=billRepo.findAll().stream().filter(b ->b.getReservationId()==id ).findFirst().orElseThrow(()->new IDNotFoundException("Reservation ID not found"));
        return mapToDTO(bill);
    }

    @Override
    public BillRespDTO payBill(long id) {
        Bill bill=billRepo.findAll().stream().filter(b ->b.getReservationId()==id ).findFirst().orElseThrow(()->new IDNotFoundException("Reservation ID not found"));
        bill.setPaymentStatus("Paid");
        //parkingSlotService.releaseSlot();
        return mapToDTO(billRepo.save(bill));
    }

    @Override
    public Double revenue() {
        return billRepo.findAll().stream().filter(bill -> bill.getPaymentStatus().equals("Paid")).map(Bill::getAmount) .reduce(0.0, Double::sum);
    }

    @Override
    public List<BillRespDTO> getAll() {
        List<BillRespDTO> billRespDTOS=billRepo.findAll().stream().map(this::mapToDTO).toList();
        return billRespDTOS;
    }

    private BillRespDTO mapToDTO(Bill bill) {
        BillRespDTO billRespDTO=new BillRespDTO();
        billRespDTO.setReservationId(bill.getReservationId());
        billRespDTO.setAmount(bill.getAmount());
        billRespDTO.setPaymentStatus(bill.getPaymentStatus());
        return billRespDTO;
    }

    public void createBill(Reservation reservation){
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
        if(duration==0){
            amount=rate;
        }
        Bill bill=new Bill();
        bill.setReservationId(reservation.getId());
        bill.setAmount(amount);
        bill.setPaymentStatus("Unpaid");
        billRepo.save(bill);
    }
}
