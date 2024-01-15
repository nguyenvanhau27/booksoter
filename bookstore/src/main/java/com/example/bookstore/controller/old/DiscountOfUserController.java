package com.example.bookstore.controller.old;


import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.Discount.DiscountOfUserRequest;
import com.example.bookstore.service.DiscountOfUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.DISCOUNT_OF_USER)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class DiscountOfUserController {

    @Autowired
    private DiscountOfUserService discountOfUserService;

    @PostMapping(value = "")
    private ResponseEntity<?> create(@RequestBody DiscountOfUserRequest discountOfUserRequest){
        return ResponseEntity.ok(discountOfUserService.create(discountOfUserRequest));
    }


    @PutMapping(value = "/{id}")
    private ResponseEntity<?> update(@PathVariable long id,
                                     @RequestBody DiscountOfUserRequest discountOfUserRequest){
        return ResponseEntity.ok(discountOfUserService.update(id, discountOfUserRequest));
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> delete(@PathVariable long id){
        return ResponseEntity.ok(discountOfUserService.delete(id));
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<?> findById(@PathVariable long id){
        return ResponseEntity.ok(discountOfUserService.findById(id));
    }

}
