package com.helper.vavahelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.helper.vavahelper.models.Lineups.Lineups;

@Repository
public interface LineupsRepository extends JpaRepository<Lineups, Long> {
    List<Lineups> findByAgents_NameIgnoreCase(String name);
    List<Lineups> findByMap_NameIgnoreCase(String name);
}
