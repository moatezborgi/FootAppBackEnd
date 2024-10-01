package com.borgi.footappbackend.controller.settingscontroller;

import com.borgi.footappbackend.dto.playerdto.PositionDto;
import com.borgi.footappbackend.dto.shareddto.NationalityDto;
import com.borgi.footappbackend.services.sharedservices.SharedServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Settings/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class SettingsController {
    private final SharedServiceInterface sharedServiceInterface;

    @GetMapping("/getAllNationality")

    public List<NationalityDto> getAllNationality()
    {
        return sharedServiceInterface.getAllNationality();

    }
    @GetMapping("/getAllPosition")
    public List<PositionDto> getAllPosition()
    {
        return sharedServiceInterface.getAllPosition();
    }
}
