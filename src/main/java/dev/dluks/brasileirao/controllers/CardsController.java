package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostRedCardsResponseDTO;
import dev.dluks.brasileirao.dtos.player.PlayersWithMostYellowCardsResponseDTO;
import dev.dluks.brasileirao.services.PlayersWithMostRedCardsService;
import dev.dluks.brasileirao.services.PlayersWithMostYellowCardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jogadores")
public class CardsController {

    @GetMapping("/mais-cartoes-amarelos")
    public ResponseEntity<PlayersWithMostYellowCardsResponseDTO> getPlayersWithMostYellowCards() {
        PlayersWithMostYellowCardsResponseDTO result = PlayersWithMostYellowCardsService.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/mais-cartoes-vermelhos")
    public ResponseEntity<PlayersWithMostRedCardsResponseDTO> getPlayersWithMostRedCards() {
        PlayersWithMostRedCardsResponseDTO result = PlayersWithMostRedCardsService.execute();
        return ResponseEntity.ok(result);
    }

}
