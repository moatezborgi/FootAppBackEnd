package com.borgi.footappbackend.controller.leaguecontroller;


import com.borgi.footappbackend.dto.playerdto.LeagueDto;
import com.borgi.footappbackend.entities.player.League;
import com.borgi.footappbackend.services.leagueservices.LeagueService;
import com.borgi.footappbackend.services.leagueservices.LeagueServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/League")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class LeagueController {

    private final LeagueServiceInterface leagueServiceInterface;
    @PostMapping("/addLeague")
    public LeagueDto addLeague(@RequestBody LeagueDto leagueDto){
        return leagueServiceInterface.addLeague(leagueDto);
    }

    @GetMapping("/getAllLeagues")
    public List<LeagueDto> getAllLeagues() {
    return leagueServiceInterface.getAllLeagues();
    }

    @PutMapping("/updateLeague")
    public LeagueDto updateLeague(@RequestBody LeagueDto leagueDto){
        return leagueServiceInterface.updateLeague(leagueDto);
    }
}
