package org.example.ppmoneytransfer.repository;

import jakarta.persistence.LockModeType;
import org.example.ppmoneytransfer.domain.Wallet;
import org.example.ppmoneytransfer.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Wallet> findWalletByOwner(Person person);
}
