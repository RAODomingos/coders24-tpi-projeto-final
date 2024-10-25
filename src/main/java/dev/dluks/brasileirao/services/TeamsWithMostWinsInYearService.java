package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.team.TeamWithMostWins;
import dev.dluks.brasileirao.dtos.team.TeamsWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.entities.Match;
import dev.dluks.brasileirao.exceptions.InvalidYearException;
import dev.dluks.brasileirao.utils.ParseYearHelper;
import dev.dluks.brasileirao.utils.SanitizeHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamsWithMostWinsInYearService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    public TeamsWithMostWinsInResponseDTO execute() {
        return execute("");
    }

    public TeamsWithMostWinsInResponseDTO execute(String year) {
        Optional<Integer> optionalYear = ParseYearHelper.parse(year);

        if (optionalYear.isPresent() &&
                (optionalYear.get() < 2003 || optionalYear.get() > 2023)
        ) {
            throw new InvalidYearException("O ano deve estar entre 2003 e 2023.");
        }
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<String, Long> winsByTeam = lines.skip(1)
                    .map(line -> new Match(SanitizeHelper.sanitize(line.split(","))))
                    .filter(match ->
                            optionalYear.isEmpty() ||
                                    match.getDate().getYear() == optionalYear.get())
                    .filter(match -> !match.getWinner().equals("-"))
                    .collect(Collectors.groupingBy(Match::getWinner, Collectors.counting()));

            List<TeamWithMostWins> teams = winsByTeam.entrySet().stream()
                    .map(entry -> new TeamWithMostWins(entry.getKey(), entry.getValue().intValue()))
                    .sorted((team1, team2) -> team2.vitorias().compareTo(team1.vitorias()))
                    .toList();

            if (optionalYear.isPresent()) {
                long maxWins = winsByTeam.values().stream()
                        .max(Long::compareTo)
                        .orElse(0L);

                teams = teams.stream()
                        .filter(team -> team.vitorias() == maxWins)
                        .toList();
            }

            return new TeamsWithMostWinsInResponseDTO(
                    optionalYear.orElse(0),
                    teams);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
