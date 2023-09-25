package com.comonitech.fraudedetective.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comonitech.fraudedetective.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
