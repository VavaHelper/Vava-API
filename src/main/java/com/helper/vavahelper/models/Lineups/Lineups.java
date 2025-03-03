package com.helper.vavahelper.models.Lineups;

import com.helper.vavahelper.models.Agents.Agents;
import com.helper.vavahelper.models.Maps.Maps;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Lineups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lineups {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agents agents;
    
    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private Maps map;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String videoUrl;
}
