package com.borgi.footappbackend.services.teamservice;


import com.borgi.footappbackend.dto.playerdto.TeamDto;
import com.borgi.footappbackend.entities.player.League;
import com.borgi.footappbackend.entities.player.Team;
import com.borgi.footappbackend.entities.shared.Nationality;
import com.borgi.footappbackend.repositories.playerrepositories.LeagueRepository;
import com.borgi.footappbackend.repositories.playerrepositories.TeamRepository;
import com.borgi.footappbackend.services.leagueservices.LeagueServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService implements TeamServiceInterface {
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final LeagueServiceInterface leagueServiceInterface;
    @Override
    public TeamDto addTeam(TeamDto teamDto) {
        League league=leagueRepository.findById(teamDto.getLeague().getLeagueId()).orElseThrow(() -> new IllegalArgumentException("Ligue non trouvée"));
        String base64Image = teamDto.getTeamLogo().split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        Team team=new Team();
        team.setTeamName(teamDto.getTeamName());
        team.setTeamLogo(imageBytes);
        team.setLeague(league);
        team= teamRepository.save(team);
        if(league.getTeamList().isEmpty())
        {
            league.setTeamList(new ArrayList<>());
        }
        league.getTeamList().add(team);
        leagueRepository.save(league);
        return teamDto;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> teams=teamRepository.findAll();
        List<TeamDto> teamDtos=new ArrayList<>();
        for(Team team:teams)
        {
            TeamDto teamDto=new TeamDto();
            teamDto.setTeamName(team.getTeamName());
             if (team.getTeamLogo() != null) {
                String base64Logo = Base64.getEncoder().encodeToString(team.getTeamLogo());
                teamDto.setTeamLogo(base64Logo);
            }
            teamDto.setTeamId(team.getTeamId());
             teamDto.setLeague(leagueServiceInterface.getLeagueById(team.getLeague().getLeagueId()));
            teamDtos.add(teamDto);

        }
        return teamDtos;
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto) {
        League league=leagueRepository.findById(teamDto.getLeague().getLeagueId()).orElseThrow(() -> new IllegalArgumentException("Ligue non trouvée"));
        Team team=teamRepository.findById(teamDto.getTeamId()).orElseThrow(() -> new IllegalArgumentException("Equipe non trouvée"));
        team.setTeamName(teamDto.getTeamName());

        if(teamDto.getTeamLogo()!=null)
        {   String base64Image = teamDto.getTeamLogo().split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            team.setTeamLogo(imageBytes);

        }
        team=teamRepository.save(team);
        team.setLeague(league);
        if(league.getTeamList().isEmpty())
        {
            league.setTeamList(new ArrayList<>());
        }
        league.getTeamList().add(team);
        leagueRepository.save(league);
        return teamDto;
    }
}
