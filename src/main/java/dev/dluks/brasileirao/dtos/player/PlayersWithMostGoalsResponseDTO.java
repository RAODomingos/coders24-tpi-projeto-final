package dev.dluks.brasileirao.dtos.player;

import java.util.List;

public record PlayersWithMostGoalsResponseDTO (
    String tipo,
    List<PlayerWithMostGoals> jogadores){}



