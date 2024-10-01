package com.borgi.footappbackend.services.playerservices;

import com.borgi.footappbackend.dto.playerdto.PlayerDto;

import java.util.List;

public interface PlayerServiceInterface {


    List<PlayerDto> getAllPlayers();
    PlayerDto addPlayer(PlayerDto player);
    PlayerDto updatePlayer(PlayerDto player);
    PlayerDto updatePlayerMarketValue(PlayerDto player);
    PlayerDto updatePlayerContract(PlayerDto player);
}
