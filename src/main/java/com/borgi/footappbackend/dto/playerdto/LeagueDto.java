package com.borgi.footappbackend.dto.playerdto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDto {
    private int leagueId;
    private int leagueNationality;
    private String leagueNationalityName;
    private String leagueName;
    private String leagueLogo;

}
