package dev.dluks.brasileirao.dtos;

import java.util.List;

public record TeamsWithMostWinsInResponseDTO(
        int ano,
        List<TeamWithMostWinsDTO> times
) {
}
