package com.borgi.footappbackend.entities.player;

import com.borgi.footappbackend.entities.shared.Nationality;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;
    private String playerFirstName;
    private String playerLastName;
    @Column(length = 500000)
    private byte[] playerImage;
    private LocalDate playerBirthDate;
    private int playerGender;
    private String playerHeight;
    private String playerWeight;
    private int playerFoot;

    @OneToMany(mappedBy = "player")
    private List<PlayerMarketValue> playerMarketValueList;

    @OneToMany(mappedBy = "player")
    private List<PlayerTeamContract> playerTeamContractList;
    @ManyToOne
    private Nationality nationality;
    @ManyToOne
    private Position position;


}
