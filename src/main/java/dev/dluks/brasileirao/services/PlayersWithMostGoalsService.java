package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.player.PlayerWithMostGoals;
import dev.dluks.brasileirao.dtos.player.PlayersWithMostGoalsResponseDTO;
import dev.dluks.brasileirao.entities.Goal;
import dev.dluks.brasileirao.exceptions.InvalidGoalTypeException;
import dev.dluks.brasileirao.utils.SanitizeHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlayersWithMostGoalsService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-gols.csv";

    public PlayersWithMostGoalsResponseDTO execute(String typeGoals) {
        if (!typeGoals.equalsIgnoreCase("contra") && !typeGoals.equalsIgnoreCase("penalti") && !typeGoals.equalsIgnoreCase("todos")) {
            throw new InvalidGoalTypeException("Utilize 'contra' para Gol Contra e 'penalti' para Penalty ou 'todos' para todos os tipos de gols");
        }

        if (typeGoals.equalsIgnoreCase("contra")) {
            typeGoals = "Gol Contra";
        }
        if (typeGoals.equalsIgnoreCase("penalti")) {
            typeGoals = "Penalty";
        }


        List<PlayerWithMostGoals> players;
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            String query = typeGoals;
            var playersWithMostGoals = lines.skip(1)
                    .map(line -> new Goal(SanitizeHelper.sanitize(line.split(","))))
                    .filter(goal -> query.equalsIgnoreCase("todos") || goal.getTypeGoal().equalsIgnoreCase(query))
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

        return new PlayersWithMostGoalsResponseDTO(typeGoals, players);
    }

}
