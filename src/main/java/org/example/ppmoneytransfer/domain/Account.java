package org.example.ppmoneytransfer.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    Person owner;

    BigDecimal balance;
}
