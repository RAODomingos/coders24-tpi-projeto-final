package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.team.TeamsWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.services.TeamsWithMostWinsInYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/times/mais-vitorias")
public class TeamController {

    private final TeamsWithMostWinsInYearService service;

    public TeamController(TeamsWithMostWinsInYearService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<TeamsWithMostWinsInResponseDTO> getTeamsWithMostWinsInYear() {
        TeamsWithMostWinsInResponseDTO result = service.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{year}")
    public ResponseEntity<TeamsWithMostWinsInResponseDTO> getTeamsWithMostWinsInYear(
            @PathVariable("year") String year
    ) {
        TeamsWithMostWinsInResponseDTO result = service.execute(year);
        return ResponseEntity.ok(result);
    }

}
