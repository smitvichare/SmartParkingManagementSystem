package com.smartparkingms.controller;

import com.smartparkingms.dto.BillRespDTO;
import com.smartparkingms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("/generate/{id}")
    private BillRespDTO generateBill(@PathVariable long id){
        return billService.generateBill(id);
    }

    @GetMapping("/pay/{id}")
    private BillRespDTO payBill(@PathVariable long id){
        return billService.payBill(id);
    }
    @GetMapping("/revenue")
    public Double revenue(){
        return billService.revenue();
    }

    @GetMapping("/all")
    public List<BillRespDTO> getAll(){
        return billService.getAll();
    }
}
