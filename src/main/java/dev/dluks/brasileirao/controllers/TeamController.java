package dev.dluks.brasileirao.controllers;

import dev.dluks.brasileirao.dtos.TeamsWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.services.TeamsWithMostWinsInYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams/most-wins")
public class TeamController {

    @GetMapping("/{year}")
    public ResponseEntity<TeamsWithMostWinsInResponseDTO> getTeamsWithMostWinsInYear(
            @PathVariable("year") int year
    ) {
        TeamsWithMostWinsInResponseDTO result = TeamsWithMostWinsInYearService.execute(year);
        return ResponseEntity.ok(result);
    }

}
