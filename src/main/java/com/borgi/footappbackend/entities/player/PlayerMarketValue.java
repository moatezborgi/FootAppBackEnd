package com.borgi.footappbackend.entities.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class PlayerMarketValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerMarketValueId;
    private String playerMarketValue;
    private LocalDate playerMarketValueDate;
    private String playerMarketValueSeason;
    private boolean isCurrentPlayerMarketValue;
    private Year playerMarketValueYear;

    @ManyToOne
    private Player player;



}
