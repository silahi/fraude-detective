package com.comonitech.fraudedetective.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

import com.comonitech.fraudedetective.enums.ParametresModeleDetection;
import com.comonitech.fraudedetective.enums.TypeModeleDetection;

@Data
@Entity
public class ModeleDetectionFraude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeModeleDetection type;

    @Enumerated(EnumType.STRING)
    private ParametresModeleDetection parametres;

    @OneToMany(mappedBy = "modeleDetection", fetch = FetchType.EAGER)
    private List<Tranzaction> transactions;
}
