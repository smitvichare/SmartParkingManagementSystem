package com.smartparkingms.controller;

import com.smartparkingms.dto.ParkingReqDTO;
import com.smartparkingms.dto.ParkingRespDTO;
import com.smartparkingms.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingSlotController {

    @Autowired
    public ParkingSlotService parkingSlotService;

    @PostMapping("/add")
    public ParkingRespDTO addSpot(@RequestBody ParkingReqDTO parkingReqDTO){
        return parkingSlotService.addSpot(parkingReqDTO);
    }

    @GetMapping("/{id}")
    public ParkingRespDTO getSpot(@PathVariable long id){
        return parkingSlotService.getSpot(id);
    }

    @GetMapping("/all")
    public List<ParkingRespDTO> getAllSpots(){
        return parkingSlotService.getAllSpots();
    }

    @DeleteMapping("/{id}")
    public void delSpot(@PathVariable long id){
        parkingSlotService.delSpot(id);
    }

    @PutMapping("/{id}")
    public ParkingRespDTO updateSpot(@PathVariable long id,@RequestBody ParkingReqDTO parkingReqDTO){
        return parkingSlotService.updateSpot(id,parkingReqDTO);
    }

    @GetMapping("/release/{id}")
    public ParkingRespDTO releaseSlot(@PathVariable long id){
        return parkingSlotService.releaseSlot(id);
    }

    @GetMapping("/filter/{vType}")
    public List<ParkingRespDTO> filterSlots(@PathVariable String vType){
        return parkingSlotService.filterSlots(vType);
    }


}
