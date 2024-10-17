package dev.dluks.brasileirao.dtos.player;

import java.util.List;

public record PlayersWithMostCardsResponseDTO(
        String cor,
        List<PlayersWithMostCards> jogadoresComMaisCartoes
) {
}
