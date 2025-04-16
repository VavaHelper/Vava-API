package com.helper.vavahelper.models.Agents;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agents {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "ult_points", nullable = false)
    private int ultPoints;

    @Column(name = "function", nullable = false)
    private String function;

    @Column(name = "icon_agent", columnDefinition = "TEXT")
    private String iconAgent;
    
    @Column(name = "img_agent", columnDefinition = "TEXT")
    private String imgAgent;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
