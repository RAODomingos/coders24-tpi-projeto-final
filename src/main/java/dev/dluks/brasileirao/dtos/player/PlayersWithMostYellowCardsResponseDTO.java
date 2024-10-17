package dev.dluks.brasileirao.dtos.player;

import java.util.List;

public record PlayersWithMostYellowCardsResponseDTO(
        List<PlayersWithMostYellowCards> jogadoresComMaisCartoesAmarelos
) {
}
