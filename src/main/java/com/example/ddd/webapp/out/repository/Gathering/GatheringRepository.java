package com.example.ddd.webapp.out.repository.Gathering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringEntity, String> {
}
