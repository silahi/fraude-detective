package com.comonitech.fraudedetective.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comonitech.fraudedetective.entities.CompteBancaire;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {

}
