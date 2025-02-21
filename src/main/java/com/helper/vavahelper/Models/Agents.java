package com.helper.vavahelper.Models;

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

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "ult_points", nullable = false)
    private int ultPoints;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
