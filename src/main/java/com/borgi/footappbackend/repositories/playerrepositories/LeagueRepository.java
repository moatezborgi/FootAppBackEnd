package com.borgi.footappbackend.repositories.playerrepositories;

import com.borgi.footappbackend.entities.player.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Number> {
}
