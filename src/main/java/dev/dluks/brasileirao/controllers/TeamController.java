package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.TeamWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.services.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estatisticas/vencedor")
public class TeamController {

    @GetMapping("/{ano}")
    public TeamWithMostWinsInResponseDTO getVencedorCampeonato(
            @PathVariable(required = false) Integer ano
    ) {
        return MatchService.getTeamsWithMostWinsInYear(ano);
    }

}
