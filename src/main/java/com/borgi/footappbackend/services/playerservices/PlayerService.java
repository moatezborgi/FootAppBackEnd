package com.borgi.footappbackend.services.playerservices;


import com.borgi.footappbackend.dto.playerdto.*;
import com.borgi.footappbackend.dto.shareddto.NationalityDto;
import com.borgi.footappbackend.entities.player.*;
import com.borgi.footappbackend.entities.shared.Nationality;
import com.borgi.footappbackend.repositories.playerrepositories.*;
import com.borgi.footappbackend.repositories.sharedrepository.NationalityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService implements PlayerServiceInterface{
    private final PlayerRepository playerRepository;
    private final NationalityRepository nationalityRepository;
    private final TeamRepository teamRepository;
    private final PositionRepository positionRepository;
    private final PlayerMarketValueRepository playerMarketValueRepository;
    private final PlayerTeamContractRepository playerTeamContractRepository;

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<PlayerDto> playerDtos = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            PlayerDto playerDto = new PlayerDto();
            playerDto.setPlayerId(player.getPlayerId());
            playerDto.setPlayerFirstName(player.getPlayerFirstName());
            playerDto.setPlayerLastName(player.getPlayerLastName());
            String base64Logo = Base64.getEncoder().encodeToString(player.getPlayerImage());

            playerDto.setPlayerImage(base64Logo);
            playerDto.setAge(LocalDate.now().getYear()-player.getPlayerBirthDate().getYear());
            playerDto.setPlayerBirthDate(player.getPlayerBirthDate());
            playerDto.setPlayerGender(player.getPlayerGender());
            playerDto.setPlayerHeight(player.getPlayerHeight());
            playerDto.setPlayerWeight(player.getPlayerWeight());
            playerDto.setPlayerFoot(player.getPlayerFoot());
            NationalityDto nationalityDto = new NationalityDto();
            nationalityDto.setNationalityId(player.getNationality().getNationalityId());
            nationalityDto.setNationalityName(player.getNationality().getNationalityName());
            playerDto.setNationalityDto(nationalityDto);
            PositionDto positionDto = new PositionDto();
            positionDto.setPositionId(player.getPosition().getPositionId());
            positionDto.setPositionName(player.getPosition().getPositionName());
            playerDto.setPositionDto(positionDto);
            if(!player.getPlayerTeamContractList().isEmpty()){
                for (PlayerTeamContract playerTeamContract:player.getPlayerTeamContractList()){
                    PlayerTeamContractDto playerTeamContractDto=new PlayerTeamContractDto();
                    playerTeamContractDto.setContractId(playerTeamContract.getContractId());
                    playerTeamContractDto.setContractValue(playerTeamContract.getContractValue());
                    playerTeamContractDto.setContractEndDate(playerTeamContract.getContractEndDate());
                    playerTeamContractDto.setContractStartDate(playerTeamContract.getContractStartDate());
                    TeamDto teamDto=new TeamDto();
                    teamDto.setTeamName(playerTeamContract.getTeam().getTeamName());
                    teamDto.setTeamId(playerTeamContract.getTeam().getTeamId());
                    LeagueDto leagueDto=new LeagueDto();
                    leagueDto.setLeagueId(playerTeamContract.getTeam().getLeague().getLeagueId());
                    leagueDto.setLeagueName(playerTeamContract.getTeam().getLeague().getLeagueName());
                    teamDto.setLeague(leagueDto);
                    playerTeamContractDto.setTeamDto(teamDto);
                    if(playerDto.getPlayerTeamContractDtos()==null)
                    {
                        playerDto.setPlayerTeamContractDtos(new ArrayList<>());
                    }
                    playerDto.getPlayerTeamContractDtos().add(playerTeamContractDto);
                }

            }
            PlayerTeamContract playerTeamContract=playerTeamContractRepository.JPQL_GetLastPlayerContract(player);
                if(playerTeamContract!=null){
                    PlayerTeamContractDto playerTeamContractDto=new PlayerTeamContractDto();
                    playerTeamContractDto.setContractId(playerTeamContract.getContractId());
                    playerTeamContractDto.setContractValue(playerTeamContract.getContractValue());
                    playerTeamContractDto.setContractEndDate(playerTeamContract.getContractEndDate());
                    playerTeamContractDto.setContractStartDate(playerTeamContract.getContractStartDate());
                    TeamDto teamDto=new TeamDto();
                    teamDto.setTeamName(playerTeamContract.getTeam().getTeamName());
                    teamDto.setTeamId(playerTeamContract.getTeam().getTeamId());
                    LeagueDto leagueDto=new LeagueDto();
                    leagueDto.setLeagueId(playerTeamContract.getTeam().getLeague().getLeagueId());
                    leagueDto.setLeagueName(playerTeamContract.getTeam().getLeague().getLeagueName());
                    teamDto.setLeague(leagueDto);
                    playerTeamContractDto.setTeamDto(teamDto);


                    playerDto.setPlayerTeamContractDto(playerTeamContractDto);
                }
                if(!player.getPlayerMarketValueList().isEmpty()){
                    for(PlayerMarketValue playerMarketValue:player.getPlayerMarketValueList()){
                        PlayerMarketValueDto playerMarketValueDto=new PlayerMarketValueDto();
                        playerMarketValueDto.setPlayerMarketValueId(playerMarketValue.getPlayerMarketValueId());
                        playerMarketValueDto.setPlayerMarketValue(playerMarketValue.getPlayerMarketValue());
                        playerMarketValueDto.setPlayerMarketValueDate(playerMarketValue.getPlayerMarketValueDate());
                        playerMarketValueDto.setPlayerMarketValueSeason(playerMarketValue.getPlayerMarketValueSeason());

                        playerMarketValueDto.setCurrentPlayerMarketValue(playerMarketValue.isCurrentPlayerMarketValue());

                        if(playerDto.getPlayerMarketValueDtos()==null)
                        {
                            playerDto.setPlayerMarketValueDtos(new ArrayList<>());

                        }
                        playerDto.getPlayerMarketValueDtos().add(playerMarketValueDto);
                    }
                }


            PlayerMarketValue playerMarketValue=playerMarketValueRepository.JPQL_getLastPlayerMarketValue(player.getPlayerId());PlayerMarketValueDto playerMarketValueDto=new PlayerMarketValueDto();
                playerMarketValueDto.setPlayerMarketValueId(playerMarketValue.getPlayerMarketValueId());
            playerMarketValueDto.setPlayerMarketValue(playerMarketValue.getPlayerMarketValue());
            playerMarketValueDto.setPlayerMarketValueDate(playerMarketValue.getPlayerMarketValueDate());
             playerDto.setPlayerMarketValueDto(playerMarketValueDto);
            playerDtos.add(playerDto);

        }
        return playerDtos;
    }

    @Override
    @Transactional
    public PlayerDto addPlayer(PlayerDto playerDto) {
        Nationality nationality=nationalityRepository.findById(playerDto.getNationalityDto().getNationalityId()).orElseThrow(()-> new RuntimeException("Nationalité non trouvée"));
        Position position=positionRepository.findById(playerDto.getPositionDto().getPositionId()).orElseThrow((() ->new RuntimeException("Position non trouvée") ));
        String base64Image = playerDto.getPlayerImage().split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        Player player=new Player();
        player.setPlayerFirstName(playerDto.getPlayerFirstName());
        player.setPlayerLastName(playerDto.getPlayerLastName());
        player.setPlayerImage(imageBytes);
        player.setPlayerBirthDate(playerDto.getPlayerBirthDate());
        player.setPlayerGender(1);
        player.setPlayerHeight(playerDto.getPlayerHeight());
        player.setPlayerWeight(playerDto.getPlayerWeight());
        player.setPlayerFoot(playerDto.getPlayerFoot());
        player.setNationality(nationality);
        player.setPosition(position);
        player=playerRepository.save(player);
        if(nationality.getPlayerList().isEmpty())
        {
            nationality.setPlayerList(new ArrayList<>());
        }

        nationality.getPlayerList().add(player);
        nationalityRepository.save(nationality);
        if(position.getPlayers().isEmpty())
        {
            position.setPlayers(new ArrayList<>());
        }
        position.getPlayers().add(player);
        positionRepository.save(position);

        PlayerMarketValue playerMarketValue=new PlayerMarketValue();
        playerMarketValue.setPlayer(player);
        playerMarketValue.setPlayerMarketValue(playerDto.getPlayerMarketValueDto().getPlayerMarketValue());
        playerMarketValue.setPlayerMarketValueDate(LocalDate.now());
        playerMarketValue.setCurrentPlayerMarketValue(true);
        int currentYear = LocalDate.now().getYear();
        playerMarketValue.setPlayerMarketValueSeason(currentYear + "/" + (currentYear + 1));
        playerMarketValue= playerMarketValueRepository.save(playerMarketValue);
        if(player.getPlayerMarketValueList()==null)
        {
            player.setPlayerMarketValueList(new ArrayList<>());
        }
        player.getPlayerMarketValueList().add(playerMarketValue);
        player= playerRepository.save(player);
        if(playerDto.getIsFreePlayer()==0) {
            Team team=teamRepository.findById(playerDto.getPlayerTeamContractDto().getTeamDto().getTeamId()).orElseThrow(()-> new RuntimeException("Equipe non trouvée"));
            PlayerTeamContract playerTeamContract = new PlayerTeamContract();
            playerTeamContract.setPlayer(player);
            playerTeamContract.setActiveContract(true);
            playerTeamContract.setContractValue(playerDto.getPlayerTeamContractDto().getContractValue());
            playerTeamContract.setContractStartDate(playerDto.getPlayerTeamContractDto().getContractStartDate());
            playerTeamContract.setContractEndDate(playerDto.getPlayerTeamContractDto().getContractEndDate());
            playerTeamContract.setTeam(team);
            playerTeamContract = playerTeamContractRepository.save(playerTeamContract);
            if (team.getPlayerTeamContractList() == null) {
                team.setPlayerTeamContractList(new ArrayList<>());
            }
            team.getPlayerTeamContractList().add(playerTeamContract);
            teamRepository.save(team);
            if (player.getPlayerTeamContractList() == null) {
                player.setPlayerTeamContractList(new ArrayList<>());
            }
            player.getPlayerTeamContractList().add(playerTeamContract);

        }
        playerRepository.save(player);
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto) {
        Nationality nationality=nationalityRepository.findById(playerDto.getNationalityDto().getNationalityId()).orElseThrow(()-> new RuntimeException("Nationalité non trouvée"));
        Position position=positionRepository.findById(playerDto.getPositionDto().getPositionId()).orElseThrow((() ->new RuntimeException("Position non trouvée") ));

        Player player=playerRepository.findById(playerDto.getPlayerId()).orElseThrow(()->new RuntimeException("Joueur non trouve"));
        if(playerDto.getPlayerImage()!=null)
        {   String base64Image = playerDto.getPlayerImage().split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            player.setPlayerImage(imageBytes);
        }
        player.setPlayerFirstName(playerDto.getPlayerFirstName());
        player.setPlayerLastName(playerDto.getPlayerLastName());
         player.setPlayerBirthDate(playerDto.getPlayerBirthDate());
        player.setPlayerGender(1);
        player.setPlayerHeight(playerDto.getPlayerHeight());
        player.setPlayerWeight(playerDto.getPlayerWeight());
        player.setPlayerFoot(playerDto.getPlayerFoot());
        player.setNationality(nationality);
        player.setPosition(position);
        player=playerRepository.save(player);
        if(nationality.getPlayerList().isEmpty())
        {
            nationality.setPlayerList(new ArrayList<>());
        }

        nationality.getPlayerList().add(player);
        nationalityRepository.save(nationality);
        if(position.getPlayers().isEmpty())
        {
            position.setPlayers(new ArrayList<>());
        }
        position.getPlayers().add(player);
        positionRepository.save(position);
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayerMarketValue(PlayerDto playerDto) {
        Player player=playerRepository.findById(playerDto.getPlayerId()).orElseThrow(()->new RuntimeException("Joueur non trouve"));
       // playerMarketValueRepository.updatePlayerMarketValue(player);
        for(PlayerMarketValue playerMarketValue:player.getPlayerMarketValueList())
        {
            playerMarketValue.setCurrentPlayerMarketValue(false);
            playerMarketValueRepository.save(playerMarketValue);
        }
        PlayerMarketValue playerMarketValue=new PlayerMarketValue();
        playerMarketValue.setPlayer(player);
        playerMarketValue.setPlayerMarketValue(playerDto.getPlayerMarketValueDto().getPlayerMarketValue());
        playerMarketValue.setPlayerMarketValueDate(LocalDate.now());
        playerMarketValue.setCurrentPlayerMarketValue(true);
        int currentYear = LocalDate.now().getYear();
        playerMarketValue.setPlayerMarketValueSeason(currentYear + "/" + (currentYear + 1));
        playerMarketValue= playerMarketValueRepository.save(playerMarketValue);
        if(player.getPlayerMarketValueList()==null)
        {
            player.setPlayerMarketValueList(new ArrayList<>());
        }
        player.getPlayerMarketValueList().add(playerMarketValue);
        player= playerRepository.save(player);
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayerContract(PlayerDto playerDto) {
        Player player=playerRepository.findById(playerDto.getPlayerId()).orElseThrow(()->new RuntimeException("Joueur non trouve"));
        for(PlayerTeamContract playerTeamContract:player.getPlayerTeamContractList())
        {
            playerTeamContract.setActiveContract(false);
            playerTeamContractRepository.save(playerTeamContract);
        }
        Team team=teamRepository.findById(playerDto.getPlayerTeamContractDto().getTeamDto().getTeamId()).orElseThrow(()-> new RuntimeException("Equipe non trouvée"));
        PlayerTeamContract playerTeamContract = new PlayerTeamContract();
        playerTeamContract.setPlayer(player);
        playerTeamContract.setActiveContract(true);
        playerTeamContract.setContractValue(playerDto.getPlayerTeamContractDto().getContractValue());
        playerTeamContract.setContractStartDate(playerDto.getPlayerTeamContractDto().getContractStartDate());
        playerTeamContract.setContractEndDate(playerDto.getPlayerTeamContractDto().getContractEndDate());
        playerTeamContract.setTeam(team);
        playerTeamContract = playerTeamContractRepository.save(playerTeamContract);
        if (team.getPlayerTeamContractList() == null) {
            team.setPlayerTeamContractList(new ArrayList<>());
        }
        team.getPlayerTeamContractList().add(playerTeamContract);
        teamRepository.save(team);
        if (player.getPlayerTeamContractList() == null) {
            player.setPlayerTeamContractList(new ArrayList<>());
        }
        player.getPlayerTeamContractList().add(playerTeamContract);
        player= playerRepository.save(player);
        return playerDto;
    }

}
