package com.helper.vavahelper.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.helper.vavahelper.models.Lineups.Lineups;
import com.helper.vavahelper.models.Lineups.body.LineupResponseDTO;
import com.helper.vavahelper.repositories.LineupsRepository;

import org.springframework.web.bind.annotation.RequestParam;



@Tag(name = "Media", description = "API for managing media files")
@RestController
@RequestMapping("/media")
@CrossOrigin(origins = "*")
public class MediaController {
    
    //injeção do repositório de lineups
    private final LineupsRepository lineupsRepository;
    public MediaController(LineupsRepository lineupsRepository) {
        this.lineupsRepository = lineupsRepository;
    }

    @GetMapping
    public List<LineupResponseDTO> getAllLineups() {
        List<Lineups> allLineups = lineupsRepository.findAll();

        return allLineups.stream()
            .map(lineup -> new LineupResponseDTO(
                lineup.getId(),
                lineup.getDescription(),
                lineup.getVideoUrl(),
                lineup.getAgents().getName(),
                lineup.getMap().getName()
            ))
            .toList();
    }
    
    @GetMapping("/agents/{name}")
    public List<Map<String, String>> getMethodNameAgent(@PathVariable String name) {
        return lineupsRepository.findByAgents_NameIgnoreCase(name)
            .stream()
            .map(lineup -> Map.of(
                "descricao", lineup.getDescription(),
                "url", lineup.getVideoUrl()
            ))
            .toList();
    }

    @GetMapping("/maps/{mapName}")
    public List<Map<String, String>> getMethodNameMap(@PathVariable String mapName) {
        return lineupsRepository.findByMap_NameIgnoreCase(mapName)
            .stream()
            .map(lineup -> Map.of(
                "descricao", lineup.getDescription(),
                "url", lineup.getVideoUrl()
            ))
            .toList();
    }
    
}
