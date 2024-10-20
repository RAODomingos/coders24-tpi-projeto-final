package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.game.MatchsWithHighestScoreResponseDTO;
import dev.dluks.brasileirao.services.MatchsWithHighestScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/partidas")
public class MatchController {

    @GetMapping("/maior-placar")
    public ResponseEntity<MatchsWithHighestScoreResponseDTO> getMatchsWithHighestScore() {
        MatchsWithHighestScoreResponseDTO result = MatchsWithHighestScoreService.execute("");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/maior-placar/{year}")
    public ResponseEntity<MatchsWithHighestScoreResponseDTO> getMatchsWithHighestScorePerYear(
            @PathVariable String year
    ) {
        MatchsWithHighestScoreResponseDTO result = MatchsWithHighestScoreService.execute(year);
        return ResponseEntity.ok(result);
    }
}
