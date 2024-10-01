package com.borgi.footappbackend.repositories.playerrepositories;

import com.borgi.footappbackend.entities.player.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Number> {
}
