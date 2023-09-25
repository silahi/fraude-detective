package com.comonitech.fraudedetective.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String numeroTelephone;
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<CompteBancaire> comptes;
}
