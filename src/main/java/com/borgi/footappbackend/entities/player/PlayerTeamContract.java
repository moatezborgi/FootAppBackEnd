package com.borgi.footappbackend.entities.player;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class PlayerTeamContract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String contractValue;
    private boolean isActiveContract;
    @ManyToOne
    private Player player;
    @ManyToOne
    private Team team;

}
