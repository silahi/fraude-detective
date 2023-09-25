package com.comonitech.fraudedetective.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comonitech.fraudedetective.entities.ModeleDetectionFraude;

@Repository
public interface ModeleDetectionFraudeRepository extends JpaRepository<ModeleDetectionFraude, Long> {

}
