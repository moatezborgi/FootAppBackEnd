package com.borgi.footappbackend.services.sharedservices;

import com.borgi.footappbackend.dto.playerdto.PositionDto;
import com.borgi.footappbackend.dto.shareddto.NationalityDto;

import java.util.List;

public interface SharedServiceInterface {

    List<NationalityDto> getAllNationality();
    List<PositionDto> getAllPosition();
}
