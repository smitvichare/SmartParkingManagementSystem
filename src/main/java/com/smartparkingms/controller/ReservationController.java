package com.smartparkingms.controller;

import com.smartparkingms.dto.ReservReqDTO;
import com.smartparkingms.dto.ReservRespDTO;
import com.smartparkingms.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReservationController {

    @Autowired
    public ReservationService reservationService;

    @PostMapping("/add")
    public ReservRespDTO addReserv(@Valid @RequestBody ReservReqDTO reservReqDTO){
        return reservationService.addReservation(reservReqDTO);
    }

    @GetMapping("/{id}")
    public ReservRespDTO getResrv(@PathVariable long id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/all")
    public List<ReservRespDTO> getAllReserv(){
        return reservationService.getAllReservation();
    }

    @DeleteMapping("/{id}")
    public void delReserv(@PathVariable long id){
        reservationService.delReservation(id);
    }

    @GetMapping("/cancel/{id}")
    public ReservRespDTO cancelReserv(@PathVariable long id){
        return reservationService.cancelReservation(id);
    }

    @GetMapping("/history/{id}")
    public List<ReservRespDTO> history(@PathVariable long id){
        return reservationService.history(id);
    }

    @PutMapping("/{id}")
    public ReservRespDTO updateReserv(@PathVariable long id, ReservReqDTO reservReqDTO){
        return reservationService.updateReservation(id,reservReqDTO);
    }
}
