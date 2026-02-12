package com.helper.vavahelper.models.Lineups.body;

public record LineupResponseDTO(
    Long id, 
    String description, 
    String videoUrl, 
    String agentName,
    String mapName) {}
