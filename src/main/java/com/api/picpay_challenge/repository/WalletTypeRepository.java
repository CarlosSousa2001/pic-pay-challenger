package com.api.picpay_challenge.repository;

import com.api.picpay_challenge.entities.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
