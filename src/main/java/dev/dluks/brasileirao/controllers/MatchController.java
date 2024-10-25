package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.game.MatchesWithHighestScoreResponseDTO;
import dev.dluks.brasileirao.services.MatchesWithHighestScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/partidas")
public class MatchController {

    private final MatchesWithHighestScoreService service;

    public MatchController(MatchesWithHighestScoreService service) {
        this.service = service;
    }

    @GetMapping("/maior-placar")
    public ResponseEntity<MatchesWithHighestScoreResponseDTO> getMatchsWithHighestScore() {
        MatchesWithHighestScoreResponseDTO result = service.execute("");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/maior-placar/{year}")
    public ResponseEntity<MatchesWithHighestScoreResponseDTO> getMatchsWithHighestScorePerYear(
            @PathVariable String year
    ) {
        MatchesWithHighestScoreResponseDTO result = service.execute(year);
        return ResponseEntity.ok(result);
    }
}
