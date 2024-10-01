package com.borgi.footappbackend.entities.player;

import com.borgi.footappbackend.entities.shared.Nationality;
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
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;
    private String teamName;
    @Column(length = 5000000)
    private byte[] teamLogo;
    @ManyToOne
    private League league;
    @OneToMany(mappedBy = "team")
    private List<PlayerTeamContract> playerTeamContractList;
}
