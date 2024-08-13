package org.example.ppmoneytransfer.repository;

import org.example.ppmoneytransfer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
