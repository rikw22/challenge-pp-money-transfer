package org.example.ppmoneytransfer.repository;

import org.example.ppmoneytransfer.domain.Wallet;
import org.example.ppmoneytransfer.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Wallet, Integer> {

    Optional<Wallet> findAccountByOwner(Person person);
}
