package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.game.MatchsWithHighestScoreResponseDTO;
import dev.dluks.brasileirao.services.MatchsWithHighestScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partidas")
public class MatchController {

    @GetMapping("/maior-placar")
    public ResponseEntity<MatchsWithHighestScoreResponseDTO> getPlayersWithMostYellowCards() {
        MatchsWithHighestScoreResponseDTO result = MatchsWithHighestScoreService.execute();
        return ResponseEntity.ok(result);
    }
}
