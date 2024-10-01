package com.borgi.footappbackend.controller.playercontroller;

import com.borgi.footappbackend.dto.playerdto.PlayerDto;
import com.borgi.footappbackend.services.playerservices.PlayerServiceInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Player/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PlayerController {
    private final PlayerServiceInterface playerServiceInterface;
    @PostMapping("/addPlayer")
    public PlayerDto addPlayer(@RequestBody PlayerDto playerDto)
    {
        return playerServiceInterface.addPlayer(playerDto);
    }

    @GetMapping("/getAllPlayers")
    public List<PlayerDto> getAllPlayers() {
    return playerServiceInterface.getAllPlayers();
    }

    @PutMapping("/updatePlayer")
    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto) {
        return playerServiceInterface.updatePlayer(playerDto);
    }

    @PutMapping("/updatePlayerMarketValue")
    public PlayerDto updatePlayerMarketValue(@RequestBody PlayerDto playerDto) {
    return playerServiceInterface.updatePlayerMarketValue(playerDto);
    }
    @PutMapping("/updatePlayerContract")
    public PlayerDto updatePlayerContract(@RequestBody PlayerDto playerDto) {
        return playerServiceInterface.updatePlayerContract(playerDto);
    }


    }
