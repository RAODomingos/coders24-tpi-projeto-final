package dev.dluks.brasileirao.dtos.team;

import java.util.List;

public record TeamsWithMostWinsInResponseDTO(
        int ano,
        List<TeamWithMostWins> times
) {
}
