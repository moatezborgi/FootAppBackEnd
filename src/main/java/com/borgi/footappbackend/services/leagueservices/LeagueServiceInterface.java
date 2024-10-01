package com.borgi.footappbackend.services.leagueservices;

import com.borgi.footappbackend.dto.playerdto.LeagueDto;
import com.borgi.footappbackend.entities.player.League;

import java.util.List;

public interface LeagueServiceInterface {

    LeagueDto addLeague(LeagueDto leagueDto);
    LeagueDto updateLeague(LeagueDto leagueDto);

    List<LeagueDto> getAllLeagues();
    LeagueDto getLeagueById(int id);
}
