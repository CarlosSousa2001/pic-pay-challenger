package com.api.picpay_challenge.controller;

import com.api.picpay_challenge.dto.TransferDTO;
import com.api.picpay_challenge.entities.Transfer;
import com.api.picpay_challenge.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {


    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")

    public ResponseEntity<Transfer> transfer(@Valid @RequestBody TransferDTO dto){
        var res = transferService.transfer(dto);
        return ResponseEntity.ok(res);
    }
}
