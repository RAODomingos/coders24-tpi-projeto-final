package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostYellowCards;
import dev.dluks.brasileirao.dtos.player.PlayersWithMostYellowCardsResponseDTO;
import dev.dluks.brasileirao.entities.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersWithMostYellowCardsService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-cartoes.csv";

    public static PlayersWithMostYellowCardsResponseDTO execute() {

        List<PlayersWithMostYellowCards> players = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<String, Long> yellowCardsPerPlayers = lines.skip(1)
                .map(line -> new Card(line.split(",")))
                .filter(card -> card.getCard().equalsIgnoreCase("Amarelo"))
                .collect(Collectors.groupingBy(Card::getAthlete, Collectors.counting()));

            Long maxYellowCards = yellowCardsPerPlayers.values().stream()
                    .max(Long::compareTo)
                    .orElse(0L);

            yellowCardsPerPlayers.entrySet().stream()
                    .filter(player -> player.getValue() == maxYellowCards)
                    .forEach(player -> players.add(new PlayersWithMostYellowCards(
                            player.getKey(), player.getValue().intValue()
                    )));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new PlayersWithMostYellowCardsResponseDTO(players);

    }

}
