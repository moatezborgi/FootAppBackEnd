package com.borgi.footappbackend.services.sharedservices;


import com.borgi.footappbackend.dto.playerdto.PositionDto;
import com.borgi.footappbackend.dto.shareddto.NationalityDto;
import com.borgi.footappbackend.entities.player.Position;
import com.borgi.footappbackend.entities.shared.Nationality;
import com.borgi.footappbackend.repositories.playerrepositories.PositionRepository;
import com.borgi.footappbackend.repositories.sharedrepository.NationalityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SharedService implements SharedServiceInterface {
    private final NationalityRepository nationalityRepository;
    private final PositionRepository positionRepository;
    @Override
    public List<NationalityDto> getAllNationality() {
        final List<Nationality> nationalityList = nationalityRepository.findAll();
        final List<NationalityDto> nationalityDtoList = new ArrayList<>();
        for (Nationality nationality : nationalityList) {
            NationalityDto nationalityDto = new NationalityDto();
            nationalityDto.setNationalityId(nationality.getNationalityId());
            nationalityDto.setNationalityName(nationality.getNationalityName());
            nationalityDtoList.add(nationalityDto);
        }
        return nationalityDtoList;
    }

    @Override
    public List<PositionDto> getAllPosition() {
        final List<Position> positionList = positionRepository.findAll();
        final List<PositionDto> positionDtoList = new ArrayList<>();
        for (Position position : positionList) {
            PositionDto positionDto = new PositionDto();
            positionDto.setPositionId(position.getPositionId());
            positionDto.setPositionName(position.getPositionName());
            positionDtoList.add(positionDto);
        }
        return positionDtoList;
    }
}
