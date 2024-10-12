package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.state.StatesWithFewestGamesResponseDTO;
import dev.dluks.brasileirao.services.StatesWithFewestGamesBetweenYearsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados/menos-jogos")
public class StateController {

    @GetMapping()
    public StatesWithFewestGamesResponseDTO getStatesWithFewestGamesBetweenYears(
            @RequestParam(name = "anoInicio", defaultValue = "2003") String startYear,
            @RequestParam(name = "anoFim", defaultValue = "2023") String endYear
    ) {

        return StatesWithFewestGamesBetweenYearsService.execute(startYear, endYear);

    }

}
