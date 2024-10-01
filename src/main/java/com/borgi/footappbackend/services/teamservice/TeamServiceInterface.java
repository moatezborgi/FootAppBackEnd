package com.borgi.footappbackend.services.teamservice;

import com.borgi.footappbackend.dto.playerdto.TeamDto;

import java.util.List;

public interface TeamServiceInterface {

    TeamDto addTeam(TeamDto teamDto);
    List<TeamDto> getAllTeams();
    TeamDto updateTeam(TeamDto teamDto);
}
