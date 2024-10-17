package dev.dluks.brasileirao.dtos.game;

import java.time.LocalDate;

public record MatchsWithHighestScore(
        String rodada,
        LocalDate data,
        String timeCasa,
        String timeVisitante,
        String ganhador,
        String estadio,
        int placarTimeCasa,
        int placarTimeVisitante,
        int placarTotal
) {
}
