package com.comonitech.fraudedetective.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comonitech.fraudedetective.entities.Tranzaction;

@Repository
public interface TransactionRepository extends JpaRepository<Tranzaction, Long> {

}
