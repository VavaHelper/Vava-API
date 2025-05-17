package com.helper.vavahelper.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.helper.vavahelper.models.Agents.Agents;
import com.helper.vavahelper.models.Skills.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Long>{
    List<Skills> findByAgent(Agents agent);
}
