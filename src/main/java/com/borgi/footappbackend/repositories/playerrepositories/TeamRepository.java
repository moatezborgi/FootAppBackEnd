package com.borgi.footappbackend.repositories.playerrepositories;

import com.borgi.footappbackend.entities.player.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Number> {
}
