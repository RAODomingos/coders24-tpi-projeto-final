package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostCardsResponseDTO;
import dev.dluks.brasileirao.services.PlayersWithMostCardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jogadores")
public class CardsController {

    @GetMapping("/mais-cartoes/{colorCard}")
    public ResponseEntity<PlayersWithMostCardsResponseDTO> getPlayersWithMostCards(@PathVariable String colorCard) {
        PlayersWithMostCardsResponseDTO result = PlayersWithMostCardsService.execute(colorCard);
        return ResponseEntity.ok(result);
    }

}
