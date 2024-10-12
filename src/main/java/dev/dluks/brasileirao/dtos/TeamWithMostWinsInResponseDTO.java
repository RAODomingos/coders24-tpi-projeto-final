package dev.dluks.brasileirao.dtos;

import java.util.List;

public record TeamWithMostWinsInResponseDTO(
        int ano,
        List<TeamWithMostWinsDTO> times
) {
}
