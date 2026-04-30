package com.SpringBoot.Controller;

import com.SpringBoot.model.Deal;
import com.SpringBoot.response.ApiResponse;
import com.SpringBoot.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {

    private  final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deals){
        Deal createDeals=dealService.createDeal(deals);
        return new ResponseEntity<>(createDeals, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,
            @RequestBody Deal deal
    ) throws Exception{
        Deal updatedDeal =dealService.updateDeal(deal,id);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeal(
            @PathVariable Long id
    ) throws Exception{
        dealService.deletedeal(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deal delete");

        return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);

    }






}
