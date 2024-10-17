package dev.dluks.brasileirao.dtos.game;

import java.util.List;

public record MatchsWithHighestScoreResponseDTO(
        List<MatchsWithHighestScore> partidasComMaiorPlacar
) {

}
