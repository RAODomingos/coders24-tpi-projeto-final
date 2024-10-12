package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.TeamWithMostWinsDTO;
import dev.dluks.brasileirao.dtos.TeamsWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.entities.Match;
import dev.dluks.brasileirao.exceptions.InvalidYearException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamsWithMostWinsInYearService {

    private TeamsWithMostWinsInYearService() {
    }

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    public static TeamsWithMostWinsInResponseDTO execute(int year) {
        if (year < 2003 || year > 2023) {
            throw new InvalidYearException("O ano deve estar entre 2003 e 2023.");
        }

        List<TeamWithMostWinsDTO> teams;
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<String, Long> winsByTeam = lines.skip(1)
                    .map(line -> new Match(line.split(",")))
                    .filter(match -> match.getDate().getYear() == year)
                    .filter(match -> !match.getWinner().equals("-"))
                    .collect(Collectors.groupingBy(Match::getWinner, Collectors.counting()));

            long maxWins = winsByTeam.values().stream()
                    .max(Long::compareTo)
                    .orElse(0L);

            teams = winsByTeam.entrySet().stream()
                    .filter(entry -> entry.getValue() == maxWins)
                    .map(entry -> new TeamWithMostWinsDTO(entry.getKey(), entry.getValue().intValue()))
                    .toList();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new TeamsWithMostWinsInResponseDTO(year, teams);

    }

}
