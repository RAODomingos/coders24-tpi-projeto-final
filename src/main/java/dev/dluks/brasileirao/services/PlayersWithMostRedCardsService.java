package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostRedCards;
import dev.dluks.brasileirao.dtos.player.PlayersWithMostRedCardsResponseDTO;
import dev.dluks.brasileirao.entities.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersWithMostRedCardsService {

    private static final String FILE_PATH = "src/main/resources/dataset/campeonato-brasileiro-cartoes.csv";

    public static PlayersWithMostRedCardsResponseDTO execute() {

        List<PlayersWithMostRedCards> players = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {

            Map<String, Long> redCardsPerPlayers = lines.skip(1)
                    .map(line -> new Card(line.split(",")))
                    .filter(card -> card.getCard().equalsIgnoreCase("Vermelho"))
                    .collect(Collectors.groupingBy(Card::getAthlete, Collectors.counting()));

            Long maxRedCards = redCardsPerPlayers.values().stream()
                    .max(Long::compareTo)
                    .orElse(0L);

            redCardsPerPlayers.entrySet().stream()
                    .filter(player -> player.getValue() == maxRedCards)
                    .forEach(player -> players.add(new PlayersWithMostRedCards(
                            player.getKey(), player.getValue().intValue()
                    )));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new PlayersWithMostRedCardsResponseDTO(players);

    }

}
