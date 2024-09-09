package org.example.ppmoneytransfer.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @NotNull
    @Column(name = "full_name", nullable  = false)
    String fullName;

    @NotNull
    @Column(name = "document", nullable  = false, unique = true)
    String document;

    @NotNull
    @Column(name = "email", nullable  = false, unique = true)
    String email;

    @NotNull
    @Column(name = "password", nullable  = false)
    String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable  = false)
    PersonType type;
}
