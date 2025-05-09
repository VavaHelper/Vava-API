package com.helper.vavahelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helper.vavahelper.models.Lineups.Lineups;

public interface LineupsRepository extends JpaRepository<Lineups, Long> {
    
}
