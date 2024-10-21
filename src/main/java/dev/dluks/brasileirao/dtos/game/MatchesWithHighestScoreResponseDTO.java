package dev.dluks.brasileirao.dtos.game;

import java.util.List;

public record MatchesWithHighestScoreResponseDTO(

        List<MatchesWithHighestScore> partidasComMaiorPlacar

) {
}
