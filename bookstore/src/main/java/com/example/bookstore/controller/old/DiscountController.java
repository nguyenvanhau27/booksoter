package com.example.bookstore.controller.old;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.Discount.DiscountRequest;
import com.example.bookstore.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.DISCOUNT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping(value = "")
    private ResponseEntity<?> create(@RequestBody DiscountRequest discountRequest){
        return ResponseEntity.ok(discountService.create(discountRequest));
    }


    @PutMapping(value = "/{id}")
    private ResponseEntity<?> update(@PathVariable long id,
                                     @RequestBody DiscountRequest discountRequest){
        return ResponseEntity.ok(discountService.update(id, discountRequest));
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> delete(@PathVariable long id){
        return ResponseEntity.ok(discountService.delete(id));
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<?> findById(@PathVariable long id){
        return ResponseEntity.ok(discountService.findById(id));
    }


    @GetMapping(value = "/findByType")
    private ResponseEntity<?> findByType(@RequestParam(name = "type", required = false) String type){
        return ResponseEntity.ok(discountService.findByType(type));
    }


    @GetMapping(value = "/findByIsDeleteNot")
    private ResponseEntity<?> findByIsDeleteNot(){
        return ResponseEntity.ok(discountService.findByIsDeleteNot());
    }

    @GetMapping(value = "/findAll")
    private ResponseEntity<?> findAll(){
        return ResponseEntity.ok(discountService.findAll());
    }


}
