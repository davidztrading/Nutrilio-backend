package org.ironhack.nutrilio.repository;

import org.ironhack.nutrilio.models.DietItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietItemRepository extends JpaRepository<DietItem, Long> {
}