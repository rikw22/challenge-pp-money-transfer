package org.example.ppmoneytransfer.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    PeopleType type;
}
