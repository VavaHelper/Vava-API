package com.helper.vavahelper.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helper.vavahelper.models.Agents.Agents;
import com.helper.vavahelper.models.Agents.body.AgentsDTO;
import com.helper.vavahelper.models.Agents.body.AgentsWithSkillsDTO;
import com.helper.vavahelper.models.Skills.body.SkillDTO;

import com.helper.vavahelper.repositories.AgentsRepository;
import com.helper.vavahelper.repositories.SkillsRepository;

@RestController
@RequestMapping("/agents")
@CrossOrigin(origins = "*")
public class AgentsController {

    @Autowired
    AgentsRepository agentsRepository;
    SkillsRepository skillsRepository;

    //All Agents
    @GetMapping
    public List<Agents> getAllAgents() {
        return agentsRepository.findAll();
    }

    //Agent by name
    @GetMapping("/{name}")
    public Agents getAgentById(@PathVariable String name) {
        return agentsRepository.findByName(name);
    }

    //Agent + Skills
    @GetMapping("/{name}/with-skills")
    public ResponseEntity<AgentsWithSkillsDTO> getAgentWithSkills(@PathVariable String name) {
        // 1) Busca o agente
        Agents agent = agentsRepository.findByName(name);

        if (agent == null) {
            return ResponseEntity.notFound().build();
        }

        // 2) Constrói o DTO do agente
        AgentsDTO agentDto = new AgentsDTO(
            agent.getName(),
            agent.getUltPoints(),
            agent.getFunction(),
            agent.getImgAgent(),
            agent.getDescription()
        );

        // 3) Busca as skills e transforma em SkillDTO
        List<SkillDTO> skillsDto = skillsRepository.findByAgent(agent)
            .stream()
            .map(skill -> new SkillDTO(
                skill.getId(),
                skill.getIconSkill(),
                skill.getName(),
                skill.getDescription(),
                agent.getName()
            ))
            .collect(Collectors.toList());

        // 4) Agrupa tudo no DTO final
        AgentsWithSkillsDTO dto = new AgentsWithSkillsDTO(agentDto, skillsDto);
        return ResponseEntity.ok(dto);
    }

    
}
