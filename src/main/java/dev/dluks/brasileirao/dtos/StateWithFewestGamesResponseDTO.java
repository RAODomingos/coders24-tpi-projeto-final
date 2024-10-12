package dev.dluks.brasileirao.dtos;

import java.util.List;
import java.util.Map;

public record StateWithFewestGamesResponseDTO(

        Map<String, Integer> periodo,
        List<StateWithFewestGamesDTO> estados

) {
}
