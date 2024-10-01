package com.borgi.footappbackend.repositories.playerrepositories;

import com.borgi.footappbackend.entities.player.Player;
import com.borgi.footappbackend.entities.player.PlayerMarketValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlayerMarketValueRepository extends JpaRepository<PlayerMarketValue, Integer> {



  @Query("""
select pm from PlayerMarketValue pm where pm.player.playerId = :player
and
pm.playerMarketValue =(select max(pm.playerMarketValue) as maxvalue from PlayerMarketValue pm where pm.player.playerId = :player)
         
""")
PlayerMarketValue JPQL_getLastPlayerMarketValue(int player);
  @Modifying
  @Query("update PlayerMarketValue pm set pm.isCurrentPlayerMarketValue=false where pm.player=:player")
  void updatePlayerMarketValue(Player player);
}
