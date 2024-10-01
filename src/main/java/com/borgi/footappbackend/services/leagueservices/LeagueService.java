package com.borgi.footappbackend.services.leagueservices;

import com.borgi.footappbackend.dto.playerdto.LeagueDto;
import com.borgi.footappbackend.entities.player.League;
import com.borgi.footappbackend.entities.shared.Nationality;
import com.borgi.footappbackend.repositories.playerrepositories.LeagueRepository;
import com.borgi.footappbackend.repositories.sharedrepository.NationalityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeagueService implements LeagueServiceInterface {
    private final LeagueRepository leagueRepository;
    private final NationalityRepository nationalityRepository;

    @Override
    public LeagueDto addLeague(LeagueDto leagueDto) {

        Nationality nationality=nationalityRepository.findById(leagueDto.getLeagueNationality()).orElseThrow(() -> new IllegalArgumentException("Nationalité non trouvée"));
        // Convertir le logo de Base64 en tableau de bytes
            String base64Image = leagueDto.getLeagueLogo().split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            League league=new League();
            league.setLeagueName(leagueDto.getLeagueName());
            league.setLeagueLogo(imageBytes);
            league.setNationality(nationality);
            league=leagueRepository.save(league);
            leagueDto.setLeagueId(league.getLeagueId());
            if(nationality.getLeagueList().isEmpty())
            {
                nationality.setLeagueList(new ArrayList<>());
            }
            nationality.getLeagueList().add(league);
            nationalityRepository.save(nationality);
         return leagueDto;
    }

    @Override
    public LeagueDto updateLeague(LeagueDto leagueDto) {
        Nationality nationality=nationalityRepository.findById(leagueDto.getLeagueNationality()).orElseThrow(() -> new IllegalArgumentException("Nationalité non trouvée"));
        League league=leagueRepository.findById(leagueDto.getLeagueId()).orElseThrow(() -> new IllegalArgumentException("Ligue non trouvée"));
        league.setLeagueName(leagueDto.getLeagueName());
        if(leagueDto.getLeagueLogo().split(",").length>1 && !leagueDto.getLeagueLogo().split(",")[1].isEmpty() )
        {   String base64Image = leagueDto.getLeagueLogo().split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            league.setLeagueLogo(imageBytes);

        }
        league.setNationality(nationality);
         leagueRepository.save(league);
        leagueDto.setLeagueId(league.getLeagueId());
        if(nationality.getLeagueList().isEmpty())
        {
            nationality.setLeagueList(new ArrayList<>());
        }
        nationality.getLeagueList().add(league);
        nationalityRepository.save(nationality);
        return leagueDto;
    }

    @Override
    public List<LeagueDto> getAllLeagues() {
        List<League> leagues = leagueRepository.findAll();
        List<LeagueDto> leagueDtos = new ArrayList<>();

        for (League league : leagues) {
            LeagueDto leagueDto = new LeagueDto();
            Nationality nationality=nationalityRepository.findById(league.getNationality().getNationalityId()).orElseThrow(() -> new IllegalArgumentException("Nationalité non trouvée"));
            leagueDto.setLeagueNationality(nationality.getNationalityId());
            leagueDto.setLeagueNationalityName(league.getNationality().getNationalityName());
            leagueDto.setLeagueId(league.getLeagueId());
            leagueDto.setLeagueName(league.getLeagueName());

             if (league.getLeagueLogo() != null) {
                String base64Logo = Base64.getEncoder().encodeToString(league.getLeagueLogo());
                leagueDto.setLeagueLogo(base64Logo);
            }

            leagueDtos.add(leagueDto);
        }

        return leagueDtos;
    }

    @Override
    public LeagueDto getLeagueById(int id) {
        League league=leagueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ligue non trouvée"));
        LeagueDto leagueDto = new LeagueDto();
        Nationality nationality=nationalityRepository.findById(league.getNationality().getNationalityId()).orElseThrow(() -> new IllegalArgumentException("Nationalité non trouvée"));
        leagueDto.setLeagueNationality(nationality.getNationalityId());
        leagueDto.setLeagueNationalityName(league.getNationality().getNationalityName());
        leagueDto.setLeagueId(league.getLeagueId());
        leagueDto.setLeagueName(league.getLeagueName());

        if (league.getLeagueLogo() != null) {
            String base64Logo = Base64.getEncoder().encodeToString(league.getLeagueLogo());
            leagueDto.setLeagueLogo(base64Logo);
        }
        return leagueDto;
    }
}
