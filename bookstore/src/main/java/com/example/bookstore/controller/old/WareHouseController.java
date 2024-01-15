package com.example.bookstore.controller.old;


import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.WareHouseRequest;
import com.example.bookstore.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiURL.WARE_HOUSE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class WareHouseController {

    @Autowired
    private WareHouseService wareHouseService;


    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody WareHouseRequest wareHouseRequest) {
        return ResponseEntity.ok(wareHouseService.create(wareHouseRequest));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> create(@PathVariable long id,
            @RequestBody WareHouseRequest wareHouseRequest) {
        return ResponseEntity.ok(wareHouseService.update(id, wareHouseRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> create(@PathVariable long id) {
        return ResponseEntity.ok(wareHouseService.delete(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return ResponseEntity.ok(wareHouseService.findById(id));
    }

    @GetMapping(value = "/findByBookNatureId/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        return ResponseEntity.ok(wareHouseService.findAllByBookNatureId(id));
    }


}
