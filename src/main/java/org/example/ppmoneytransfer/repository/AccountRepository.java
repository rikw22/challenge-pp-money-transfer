package org.example.ppmoneytransfer.repository;

import org.example.ppmoneytransfer.domain.Account;
import org.example.ppmoneytransfer.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
