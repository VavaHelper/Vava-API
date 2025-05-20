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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;


@Tag(name = "Agents", description = "API for managing agents and their skills")
@RestController
@RequestMapping("/agents")
@CrossOrigin(origins = "*")
public class AgentsController {

    @Autowired
    AgentsRepository agentsRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Operation(
        summary = "Get all agents",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of agents", 
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Agents.class)))
        }
    )

    //All Agents
    @GetMapping
    public List<Agents> getAllAgents() {
        return agentsRepository.findAll();
    }

    //Agent by name
    @Operation(
        summary = "Get an agent by name",
        parameters = {
            @Parameter(name = "name", description = "Name of the agent to retrieve", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Agent found", 
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Agents.class))),
            @ApiResponse(responseCode = "404", description = "Agent not found")
        }
    )
    @GetMapping("/{name}")
    public Agents getAgentById(@PathVariable String name) {
        return agentsRepository.findByName(name);
    }

    //Agent + Skills
    @Operation(
        summary = "Get an agent along with their skills",
        parameters = {
            @Parameter(name = "name", description = "Name of the agent", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Agent and skills returned",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AgentsWithSkillsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Agent not found")
        }
    )
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
