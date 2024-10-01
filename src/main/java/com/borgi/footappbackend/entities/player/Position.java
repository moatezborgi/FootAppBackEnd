package com.borgi.footappbackend.entities.player;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int positionId;
    private String positionName;
    @OneToMany(mappedBy = "position")
    private List<Player> players;

    // Constructeur
    public Position(int positionId, String positionName) {
        this.positionId = positionId;
        this.positionName = positionName;
    }



}
