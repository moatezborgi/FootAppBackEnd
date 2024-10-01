package com.borgi.footappbackend.repositories.playerrepositories;

import com.borgi.footappbackend.entities.player.Player;
import com.borgi.footappbackend.entities.player.PlayerTeamContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerTeamContractRepository extends JpaRepository<PlayerTeamContract, Number> {

    @Query("""
select pc  from PlayerTeamContract pc
where
pc.player=:player
and
pc.isActiveContract=true
""")
    PlayerTeamContract JPQL_GetLastPlayerContract(Player player);
}
