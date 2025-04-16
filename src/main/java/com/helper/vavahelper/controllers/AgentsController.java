package com.helper.vavahelper.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helper.vavahelper.models.Agents.Agents;
import com.helper.vavahelper.repositories.AgentsRepository;

@RestController
@RequestMapping("/agents")
@CrossOrigin(origins = "*")
public class AgentsController {

    @Autowired
    AgentsRepository agentsRepository;

    @GetMapping("/{name}")
    public Agents getAgentById(@PathVariable String name) {
        return agentsRepository.findByName(name);
    }

    @GetMapping
    public List<Agents> getAllAgents() {
        return agentsRepository.findAll();
    }
}
