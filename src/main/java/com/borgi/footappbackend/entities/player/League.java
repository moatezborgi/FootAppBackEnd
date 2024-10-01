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
public class League implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leagueId;
    private String leagueName;
    @Column(length = 500000)
    private byte[] leagueLogo;
     @OneToMany(mappedBy = "league")
    private List<Team> teamList;
     @ManyToOne
    private Nationality nationality;
}
