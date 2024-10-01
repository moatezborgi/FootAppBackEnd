package com.borgi.footappbackend.dto.playerdto;

import com.borgi.footappbackend.entities.player.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTeamContractDto {
    private int contractId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String contractValue;
    private TeamDto teamDto;
    private boolean isActiveContract;
}
