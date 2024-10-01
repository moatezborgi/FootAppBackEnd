package com.borgi.footappbackend.repositories.playerrepositories;

import com.borgi.footappbackend.entities.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Number> {
}
