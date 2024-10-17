package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostCards;
import dev.dluks.brasileirao.dtos.player.PlayersWithMostCardsResponseDTO;
import dev.dluks.brasileirao.entities.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersWithMostCardsService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-cartoes.csv";

    public static PlayersWithMostCardsResponseDTO execute(String colorCard) {

        List<PlayersWithMostCards> players = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<String, Long> cardsPerPlayers = lines.skip(1)
                .map(line -> new Card(line.split(",")))
                .filter(card -> card.getCard().equalsIgnoreCase(colorCard))
                .collect(Collectors.groupingBy(Card::getAthlete, Collectors.counting()));

            Long maxCards = cardsPerPlayers.values().stream()
                .max(Long::compareTo)
                .orElse(0L);

            cardsPerPlayers.entrySet().stream()
                .filter(player -> player.getValue() == maxCards)
                .forEach(player -> players.add(new PlayersWithMostCards(
                        player.getKey(), player.getValue().intValue()
                )));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new PlayersWithMostCardsResponseDTO(colorCard, players);

    }

}
