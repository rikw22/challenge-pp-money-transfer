package org.example.ppmoneytransfer.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    Person owner;

    @Column(name = "balance", nullable  = false)
    BigDecimal balance;
}
