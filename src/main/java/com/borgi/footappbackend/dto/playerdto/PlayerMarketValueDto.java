package com.borgi.footappbackend.dto.playerdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerMarketValueDto {

    private int playerMarketValueId;
    private String playerMarketValue;
    private LocalDate playerMarketValueDate;
    private String playerMarketValueSeason;
    private boolean isCurrentPlayerMarketValue;
    private Year playerMarketValueYear;

}
