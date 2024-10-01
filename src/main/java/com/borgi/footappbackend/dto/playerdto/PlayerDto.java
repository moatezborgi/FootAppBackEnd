package com.borgi.footappbackend.dto.playerdto;


import com.borgi.footappbackend.dto.shareddto.NationalityDto;
import com.borgi.footappbackend.entities.player.PlayerMarketValue;
import com.borgi.footappbackend.entities.player.PlayerTeamContract;
import com.borgi.footappbackend.entities.player.Position;
import com.borgi.footappbackend.entities.shared.Nationality;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private int playerId;
    private String playerFirstName;
    private String playerLastName;
    private String playerImage;
    private LocalDate playerBirthDate;
    private int age;
    private int playerGender; //1 homme
    private String playerHeight;
    private String playerWeight;
    private int playerFoot; // 1 pied droit - 2 pied gauche -  3 deux pieds
    private int isFreePlayer;
    private PositionDto positionDto;
    private NationalityDto nationalityDto;
    private PlayerMarketValueDto playerMarketValueDto;
    private PlayerTeamContractDto playerTeamContractDto;
    private List<PlayerMarketValueDto> playerMarketValueDtos;
    private List<PlayerTeamContractDto> playerTeamContractDtos;


}
