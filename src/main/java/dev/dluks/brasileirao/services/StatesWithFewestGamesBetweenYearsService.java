package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.state.StateWithFewestGames;
import dev.dluks.brasileirao.dtos.state.StatesWithFewestGamesResponseDTO;
import dev.dluks.brasileirao.entities.Match;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatesWithFewestGamesBetweenYearsService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    private StatesWithFewestGamesBetweenYearsService() {
    }

    public static StatesWithFewestGamesResponseDTO execute(String sYear, String eYear) {
        int startYear;
        int endYear;

        try {
            startYear = Integer.parseInt(sYear);
        } catch (NumberFormatException e) {
            startYear = 2003;
        }

        try {
            endYear = Integer.parseInt(eYear);
        } catch (NumberFormatException e) {
            endYear = 2023;
        }

        if (startYear < 2003 || startYear > 2023) {
            startYear = 2003;
        }
        if (endYear < 2003 || endYear > 2023) {
            endYear = 2023;
        }

        if (endYear < startYear) {
            int temp = startYear;
            startYear = endYear;
            endYear = temp;
        }

        int finalStartYear = startYear;
        int finalEndYear = endYear;

        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            Map<String, Long> gamesByState = lines.skip(1)
                    .map(line -> new Match(line.split(",")))
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

}
