package com.api.picpay_challenge.config;

import com.api.picpay_challenge.entities.Wallet;
import com.api.picpay_challenge.entities.WalletType;
import com.api.picpay_challenge.repository.WalletRepository;
import com.api.picpay_challenge.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    private final WalletRepository walletRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository, WalletRepository walletRepository) {
        this.walletTypeRepository = walletTypeRepository;
        this.walletRepository = walletRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Arrays.stream(WalletType.Enum.values())
                .forEach(walletType -> walletTypeRepository.save(walletType.get()));


        Wallet wallet1 = new Wallet();

        wallet1.setFullName("Carlos Sousa");
        wallet1.setCpfCnpj("123");
        wallet1.setEmail("carlosteste@gmail.com");
        wallet1.setPassword("123456");
        wallet1.setBalance(BigDecimal.valueOf(1000));
        wallet1.setWalletType(WalletType.Enum.USER.get());

        walletRepository.save(wallet1);

        Wallet wallet2 = new Wallet();

        wallet2.setFullName("Joao Sousa");
        wallet2.setCpfCnpj("456");
        wallet2.setEmail("Joao@gmail.com");
        wallet2.setPassword("123456");
        wallet2.setBalance(BigDecimal.valueOf(2000));
        wallet2.setWalletType(WalletType.Enum.USER.get());

        walletRepository.save(wallet2);

        Wallet wallet3 = new Wallet();

        wallet3.setFullName("Maria");
        wallet3.setCpfCnpj("789");
        wallet3.setEmail("maria@gmail.com");
        wallet3.setPassword("123456");
        wallet3.setBalance(BigDecimal.valueOf(3000));
        wallet3.setWalletType(WalletType.Enum.MERCHANT.get());

        walletRepository.save(wallet3);


    }
}
