package com.helper.vavahelper.models.Maps;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.helper.vavahelper.models.Lineups.Lineups;

@Entity
@Table(name = "Maps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maps {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lineups> lineups;
}
