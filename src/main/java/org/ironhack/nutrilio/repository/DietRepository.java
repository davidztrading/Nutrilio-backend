package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
}