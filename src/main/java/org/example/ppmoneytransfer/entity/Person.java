package org.example.ppmoneytransfer.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @NotNull
    String fullName;

    @NotNull
    String document;

    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    PersonType type;
}
