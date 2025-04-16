package com.helper.vavahelper.models.Skills;

import com.helper.vavahelper.models.Agents.Agents;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "icon_skill", columnDefinition = "TEXT")
    private String iconSkill;
    
    @ManyToOne
    @JoinColumn(name = "agente_id", nullable = false)
    private Agents agent;
    
    @Column(name = "nome", nullable = false, length = 50)
    private String name;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String description;
}