package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.state.StateWithFewestGames;
import dev.dluks.brasileirao.dtos.state.StatesWithFewestGamesResponseDTO;
import dev.dluks.brasileirao.entities.Match;
import dev.dluks.brasileirao.exceptions.InvalidYearException;
import dev.dluks.brasileirao.utils.SanitizeHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatesWithFewestGamesBetweenYearsService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    private StatesWithFewestGamesBetweenYearsService() {
    }

    public static StatesWithFewestGamesResponseDTO execute(String sYear, String eYear) {

        Optional<Integer> startYear = parseYear(sYear);
        Optional<Integer> endYear = parseYear(eYear);

        if (startYear.isEmpty() || endYear.isEmpty()) {
            throw new InvalidYearException("Os anos de início e fim são obrigatórios");
        }

        if (startYear.get() < 2003 || startYear.get() > 2023) {
            throw new InvalidYearException("O ano de início deve estar entre 2003 e 2023");
        }
        if (endYear.get() < 2003 || endYear.get() > 2023) {
            throw new InvalidYearException("O ano de fim deve estar entre 2003 e 2023");
        }

        if (endYear.get() < startYear.get()) {
            throw new InvalidYearException("O ano de fim deve ser maior que o ano de início");
        }

        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            Integer finalStartYear = startYear.get();
            Integer finalEndYear = endYear.get();

            Map<String, Long> gamesByState = lines.skip(1)
                    .map(line -> new Match(SanitizeHelper.sanitize(line.split(","))))
                    .filter(match -> match.getDate().getYear() >= finalStartYear && match.getDate().getYear() <= finalEndYear)
                    .collect(Collectors.groupingBy(Match::getHomeTeamState, Collectors.counting()));

            long minGames = gamesByState.values().stream()
                    .min(Long::compareTo)
                    .orElse(0L);

            List<StateWithFewestGames> stateWithFewestGames = gamesByState.entrySet().stream()
                    .filter(entry -> entry.getValue() == minGames)
                    .map(entry -> new StateWithFewestGames(entry.getKey(), entry.getValue().intValue()))
                    .toList();

            Map<String, Integer> period = new HashMap<>();
            period.put("anoInicio", finalStartYear);
            period.put("anoFim", finalEndYear);

            return new StatesWithFewestGamesResponseDTO(period, stateWithFewestGames);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Optional<Integer> parseYear(String year) {
        if (year == null || year.isBlank()) {
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(year));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("O ano %s é inválido", year));
        }
    }
}
