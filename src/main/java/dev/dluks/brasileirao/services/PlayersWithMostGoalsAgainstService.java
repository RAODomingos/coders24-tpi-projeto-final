package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.player.PlayerWithMostGoals;
import dev.dluks.brasileirao.dtos.player.PlayersWithMostGoalsResponseDTO;
import dev.dluks.brasileirao.entities.Goal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersWithMostGoalsAgainstService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-gols.csv";

    public static PlayersWithMostGoalsResponseDTO execute() {

        List<PlayerWithMostGoals> players;
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            var playersWithMostGoals = lines.skip(1)
                    .map(line -> new Goal(line.split(",")))
                    .filter(goal -> goal.getTypeGoal().equalsIgnoreCase("Gol Contra"))
                    .collect(Collectors.groupingBy(Goal::getAthlete, Collectors.counting()));

            long maxGoals = playersWithMostGoals.values().stream()
                    .max(Long::compareTo)
                    .orElse(0L);

            players = playersWithMostGoals.entrySet().stream()
                    .filter(entry -> entry.getValue() == maxGoals)
                    .map(entry -> new PlayerWithMostGoals(entry.getKey(), entry.getValue().intValue()))
                    .toList();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new PlayersWithMostGoalsResponseDTO(players);
    }

}
