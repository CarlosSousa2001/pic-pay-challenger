package com.api.picpay_challenge.service;

import com.api.picpay_challenge.dto.TransferDTO;
import com.api.picpay_challenge.entities.Transfer;
import com.api.picpay_challenge.entities.Wallet;
import com.api.picpay_challenge.exceptions.InsufficientBalanceExeption;
import com.api.picpay_challenge.exceptions.TransferNotAllowedForWallettypeException;
import com.api.picpay_challenge.exceptions.TransferNotAuthorizedException;
import com.api.picpay_challenge.exceptions.WalletNotFoundException;
import com.api.picpay_challenge.repository.TransferRepository;
import com.api.picpay_challenge.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;

    private final WalletRepository walletRepository;

    public TransferService(NotificationService notificationService, AuthorizationService authorizationService, TransferRepository transferRepository, WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {

        var sender = walletRepository.findById(transferDTO.payer()).orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payee()).orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        var transfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var result = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotifation(result));

        return result;
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWallettypeException();
        }

        if (!sender.isBalancerEqualOrGreather(transferDTO.value())) {
            throw new InsufficientBalanceExeption();
        }

        if (!authorizationService.IsAuthorized(transferDTO)) {
            throw new TransferNotAuthorizedException();
        }


    }
}
