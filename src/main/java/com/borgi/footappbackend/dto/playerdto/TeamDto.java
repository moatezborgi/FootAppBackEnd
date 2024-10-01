package com.borgi.footappbackend.dto.playerdto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private int teamId;
    private String teamName;
    private String teamLogo;
    private LeagueDto league;
}
