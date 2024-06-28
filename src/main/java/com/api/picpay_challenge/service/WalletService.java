package com.api.picpay_challenge.service;

import com.api.picpay_challenge.dto.CreateWalletDTO;
import com.api.picpay_challenge.entities.Wallet;
import com.api.picpay_challenge.exceptions.WalletDataAlreadyExistsException;
import com.api.picpay_challenge.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    public Wallet creareWallet(CreateWalletDTO createWalletDTO) {

        var walletDv = walletRepository.findByCpfCnpjOrEmail(createWalletDTO.cpfCnpj(), createWalletDTO.email());

        if (walletDv.isPresent()) {
            throw new WalletDataAlreadyExistsException("cpfCnpj or email already exits");
        }

        return walletRepository.save(createWalletDTO.toWallet());
    }
}
