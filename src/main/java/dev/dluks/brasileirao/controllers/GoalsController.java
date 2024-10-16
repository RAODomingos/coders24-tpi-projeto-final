package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostGoalsResponseDTO;
import dev.dluks.brasileirao.dtos.team.TeamsWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.services.PlayersWithMostGoalsAgainstService;
import dev.dluks.brasileirao.services.PlayersWithMostGoalsService;
import dev.dluks.brasileirao.services.PlayersWithMostPenaltyGoalsService;
import dev.dluks.brasileirao.services.TeamsWithMostWinsInYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/jogadores")
public class GoalsController {

    @GetMapping("/mais-gols")
    public ResponseEntity<PlayersWithMostGoalsResponseDTO> getPlayersWithMostGoals() {
      PlayersWithMostGoalsResponseDTO result = PlayersWithMostGoalsService.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/gols-penalty")
    public ResponseEntity<PlayersWithMostGoalsResponseDTO> getPlayersWithMostGoalsPenalty() {
        PlayersWithMostGoalsResponseDTO result = PlayersWithMostPenaltyGoalsService.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/gols-contra")
    public ResponseEntity<PlayersWithMostGoalsResponseDTO> getPlayersWithMostGoalsAgainst() {
        PlayersWithMostGoalsResponseDTO result = PlayersWithMostGoalsAgainstService.execute();
        return ResponseEntity.ok(result);
    }


}
