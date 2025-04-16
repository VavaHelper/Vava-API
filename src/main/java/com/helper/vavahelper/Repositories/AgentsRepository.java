package com.helper.vavahelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helper.vavahelper.models.Agents.Agents;

public interface AgentsRepository extends JpaRepository<Agents, Long>{
    
}
