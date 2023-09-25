package com.comonitech.fraudedetective.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comonitech.fraudedetective.entities.AlerteFraude;

@Repository
public interface AlerteFraudeRepository extends JpaRepository<AlerteFraude, Long> {

}
