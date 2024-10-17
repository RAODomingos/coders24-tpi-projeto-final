package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.game.MatchsWithHighestScore;
import dev.dluks.brasileirao.dtos.game.MatchsWithHighestScoreResponseDTO;
import dev.dluks.brasileirao.entities.Match;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchsWithHighestScoreService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    public static MatchsWithHighestScoreResponseDTO execute() {

        List<MatchsWithHighestScore> matchs = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<Integer, List<Match>> matchsPerTotalScore = lines.skip(1)
                    .map(line -> new Match(line.split(",")))
                    .collect(Collectors.groupingBy(Match::getTotalScore));

            var maxTotalScore = matchsPerTotalScore.keySet().stream()
                    .max(Integer::compareTo)
                    .orElse(0);

            matchsPerTotalScore.get(maxTotalScore).stream()
                    .forEach(match -> {
                        matchs.add(new MatchsWithHighestScore(
                                match.getRound(),
                                match.getDate(),
                                match.getHomeTeam(),
                                match.getVisitorTeam(),
                                match.getWinner(),
                                match.getStadium(),
                                match.getHomeTeamScore(),
                                match.getVisitorTeamScore(),
                                match.getTotalScore()
                        ));
                    });

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new MatchsWithHighestScoreResponseDTO(matchs);

    }
}
