package com.helper.vavahelper.models.Agents.body;

import java.util.List;
import com.helper.vavahelper.models.Skills.body.SkillDTO;

public record AgentsWithSkillsDTO( AgentsDTO agent, List<SkillDTO> skills ) {}
