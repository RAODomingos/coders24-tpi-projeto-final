package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.StateWithFewestGamesResponseDTO;
import dev.dluks.brasileirao.services.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estatisticas/estado")
public class StateController {

    @GetMapping("/menos-jogos")
    public StateWithFewestGamesResponseDTO getEstadoComMenosJogos(
            @RequestParam(name = "anoInicio", defaultValue = "2003") String anoInicio,
            @RequestParam(name = "anoFim", defaultValue = "2023") String anoFim
    ) {

        return MatchService.getStateWithFewestGamesBetweenYears(anoInicio, anoFim);

    }

}
