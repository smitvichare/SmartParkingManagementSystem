package com.smartparkingms.service;

import com.smartparkingms.dto.BillRespDTO;

import java.util.List;

public interface BillService {
    BillRespDTO generateBill(long id);

    BillRespDTO payBill(long id);

    Double revenue();

    List<BillRespDTO> getAll();
}
