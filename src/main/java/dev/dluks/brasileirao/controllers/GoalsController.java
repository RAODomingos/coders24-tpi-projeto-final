package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostGoalsResponseDTO;
import dev.dluks.brasileirao.services.PlayersWithMostGoalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jogadores")
public class GoalsController {

    private final PlayersWithMostGoalsService service;

    public GoalsController(PlayersWithMostGoalsService service) {
        this.service = service;
    }

    @GetMapping("/mais-gols")
    public ResponseEntity<PlayersWithMostGoalsResponseDTO> getPlayersWithMostGoals() {
        PlayersWithMostGoalsResponseDTO result = service.execute("todos");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/mais-gols/{typeGoals}")
    public ResponseEntity<PlayersWithMostGoalsResponseDTO> getPlayersWithMostGoals(@PathVariable String typeGoals) {
        PlayersWithMostGoalsResponseDTO result = service.execute(typeGoals);
        return ResponseEntity.ok(result);
    }


}
