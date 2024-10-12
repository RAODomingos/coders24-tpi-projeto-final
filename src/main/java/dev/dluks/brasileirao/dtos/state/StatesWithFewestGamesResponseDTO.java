package dev.dluks.brasileirao.dtos.state;

import java.util.List;
import java.util.Map;

public record StatesWithFewestGamesResponseDTO(

        Map<String, Integer> periodo,
        List<StateWithFewestGames> estados

) {
}
