package com.example.bookstore.controller;


import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.address.AddressRequest;
import com.example.bookstore.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.ADDRESS)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(value = "")
    private ResponseEntity<?> create(@RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(addressService.create(addressRequest));
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<?> update(@PathVariable int id,
                                     @RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(addressService.update(id,addressRequest));
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(addressService.delete(id));
    }

    @GetMapping(value = "/{userId}")
    private ResponseEntity<?> findByUserId(@PathVariable String userId){
        return ResponseEntity.ok(addressService.findByUserId(userId));
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<?> findById(@PathVariable int id){
        return ResponseEntity.ok(addressService.findById(id));
    }
}
