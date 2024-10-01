package com.borgi.footappbackend.controller.teamcontroller;


import com.borgi.footappbackend.dto.playerdto.TeamDto;
import com.borgi.footappbackend.services.teamservice.TeamServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Team/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class TeamController {
    private final TeamServiceInterface teamServiceInterface;

    @PostMapping("addTeam")
    public TeamDto addTeam(@RequestBody TeamDto teamDto)
    {
        return teamServiceInterface.addTeam(teamDto);
    }
    @GetMapping("getAllTeams")
    public List<TeamDto> getAllTeams()
    {
        return teamServiceInterface.getAllTeams();
    }
    @PutMapping("updateTeam")
    public TeamDto updateTeam(@RequestBody TeamDto teamDto){
        return teamServiceInterface.updateTeam(teamDto);
    }
}
