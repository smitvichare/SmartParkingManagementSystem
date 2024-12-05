package com.smartparkingms.service;

import com.smartparkingms.dto.BillRespDTO;
import com.smartparkingms.exception.IDNotFoundException;
import com.smartparkingms.model.Bill;
import com.smartparkingms.repository.BillRepo;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    BillRepo billRepo;

    @Override
    public BillRespDTO generateBill(long id) {
        Bill bill=billRepo.findAll().stream().filter(b ->b.getReservationId()==id ).findFirst().orElseThrow(()->new IDNotFoundException("Reservation ID not found"));
        return mapToDTO(bill);
    }

    @Override
    public BillRespDTO payBill(long id) {
        Bill bill=billRepo.findAll().stream().filter(b ->b.getReservationId()==id ).findFirst().orElseThrow(()->new IDNotFoundException("Reservation ID not found"));
        bill.setPaymentStatus("Paid");
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
}
