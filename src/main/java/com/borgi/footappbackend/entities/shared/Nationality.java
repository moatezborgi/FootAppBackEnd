package com.borgi.footappbackend.entities.shared;

import com.borgi.footappbackend.entities.player.League;
import com.borgi.footappbackend.entities.player.Player;
import com.borgi.footappbackend.entities.player.Team;
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
public class Nationality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nationalityId;
    private String nationalityName;
    @OneToMany(mappedBy = "nationality")
    private List<League> leagueList;
    @OneToMany(mappedBy = "nationality")
    private List<Player> playerList;

    public Nationality(int i, String vanuatu) {
        this.nationalityId = i;
        this.nationalityName = vanuatu;
    }
}
