package com.api.picpay_challenge.dto;

import com.api.picpay_challenge.entities.Wallet;
import com.api.picpay_challenge.entities.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDTO(@NotBlank String fullName, @NotEmpty String cpfCnpj, @NotEmpty String email, @NotEmpty String password, @NotNull WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(fullName, cpfCnpj, email, password, walletType.get());
    }
}
