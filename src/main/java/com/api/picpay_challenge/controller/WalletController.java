package com.api.picpay_challenge.controller;

import com.api.picpay_challenge.dto.CreateWalletDTO;
import com.api.picpay_challenge.entities.Wallet;
import com.api.picpay_challenge.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;


    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@Valid  @RequestBody CreateWalletDTO createWalletDTO) {
        var wallet = walletService.creareWallet(createWalletDTO);

        return ResponseEntity.ok(wallet);
    }
}
