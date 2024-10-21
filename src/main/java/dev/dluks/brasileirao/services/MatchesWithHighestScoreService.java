package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.game.MatchesWithHighestScore;
import dev.dluks.brasileirao.dtos.game.MatchesWithHighestScoreResponseDTO;
import dev.dluks.brasileirao.entities.Match;
import dev.dluks.brasileirao.utils.ParseYearHelper;
import dev.dluks.brasileirao.utils.SanitizeHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchesWithHighestScoreService {

    private MatchesWithHighestScoreService() {
    }

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    public static MatchesWithHighestScoreResponseDTO execute(String year) {
        Optional<Integer> optionalYear = ParseYearHelper.parse(year);

        List<MatchesWithHighestScore> matches = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<Integer, List<Match>> matchesPerTotalScore = lines.skip(1)
                    .map(line -> new Match(SanitizeHelper.sanitize(line.split(","))))
                    .filter(match ->
                            optionalYear
                                    .map(integer -> match.getDate().getYear() == integer)
                                    .orElse(true)
                    )
                    .collect(Collectors.groupingBy(Match::getTotalScore));

            var maxTotalScore = matchesPerTotalScore.keySet().stream()
                    .max(Integer::compareTo)
                    .orElse(0);

            matchesPerTotalScore.get(maxTotalScore)
                    .forEach(match ->
                            matches.add(new MatchesWithHighestScore(
                                    match.getRound(),
                                    match.getDate(),
                                    match.getHomeTeam(),
                                    match.getVisitorTeam(),
                                    match.getWinner(),
                                    match.getStadium(),
                                    match.getHomeTeamScore(),
                                    match.getVisitorTeamScore(),
                                    match.getTotalScore()
                            ))
                    );

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new MatchesWithHighestScoreResponseDTO(matches);
    }

}
