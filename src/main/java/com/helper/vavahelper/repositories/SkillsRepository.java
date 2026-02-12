package com.helper.vavahelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helper.vavahelper.models.Agents.Agents;
import com.helper.vavahelper.models.Skills.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long>{
    List<Skills> findByAgent(Agents agent);
}
