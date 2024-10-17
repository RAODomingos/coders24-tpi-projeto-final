package dev.dluks.brasileirao.dtos.player;

import java.util.List;

public record PlayersWithMostRedCardsResponseDTO(
        List<PlayersWithMostRedCards> jogadoresComMaisCartoesVermelhos
) {
}
