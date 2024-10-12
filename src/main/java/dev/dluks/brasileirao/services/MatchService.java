package dev.dluks.brasileirao.services;

import dev.dluks.brasileirao.dtos.*;
import dev.dluks.brasileirao.entities.Match;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchService {

    private static String filePath = "src/main/resources/dataset/campeonato-brasileiro-full.csv";

    private static List<Match> matches;

    public static TeamWithMostWinsInResponseDTO getTeamsWithMostWinsInYear(int year) {
        if (year < 2003 || year > 2023) {
            year = 2023;
        }

        List<TeamWithMostWinsDTO> teams;

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            int finalFilteredYear = year;

            // Agrupa os times por número de vitórias
            Map<String, Long> winsByTeam = lines.skip(1) // Pula o cabeçalho
                    .map(line -> new Match(line.split(","))) // Mapeia para objetos Match
                    .filter(match -> match.getDate().getYear() == finalFilteredYear) // Filtra por ano
                    .filter(match -> !match.getWinner().equals("-")) // Ignora empates (onde o winner é "-")
                    .collect(Collectors.groupingBy(Match::getWinner, Collectors.counting())); // Agrupa por time e conta as vitórias

            // Encontra o número máximo de vitórias
            long maxWins = winsByTeam.values().stream()
                    .max(Long::compareTo)
                    .orElse(0L); // Define 0 caso não tenha nenhum resultado

            // Filtra os times que possuem o número máximo de vitórias
            teams = winsByTeam.entrySet().stream()
                    .filter(entry -> entry.getValue() == maxWins) // Filtra times com o maior número de vitórias
                    .map(entry -> new TeamWithMostWinsDTO(entry.getKey(), entry.getValue().intValue())) // Mapeia para DTO
                    .collect(Collectors.toList()); // Coleta em uma lista de DTOs

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new TeamWithMostWinsInResponseDTO(year, teams);
    }

    public static StateWithFewestGamesResponseDTO getStateWithFewestGamesBetweenYears(String sYear, String eYear) {
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

        // Verifica se os anos estão no intervalo de 2003 a 2023
        if (startYear < 2003 || startYear > 2023) {
            startYear = 2003;
        }
        if (endYear < 2003 || endYear > 2023) {
            endYear = 2023;
        }
        // Verifica se o ano final é maior que o ano inicial
        if (endYear < startYear) {
            int temp = startYear;
            startYear = endYear;
            endYear = temp;
        }

        int finalStartYear = startYear;
        int finalEndYear = endYear;

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            // Agrupa os estados por quantidade de partidas
            Map<String, Long> gamesByState = lines.skip(1) // Pula o cabeçalho
                    .map(line -> new Match(line.split(","))) // Mapeia para objetos Match
                    .filter(match -> match.getDate().getYear() >= finalStartYear && match.getDate().getYear() <= finalEndYear) // Filtra por intervalo de anos
                    .collect(Collectors.groupingBy(Match::getHomeTeamState, Collectors.counting())); // Agrupa por estado e conta as partidas

            // Encontra o número mínimo de partidas
            long minGames = gamesByState.values().stream()
                    .min(Long::compareTo)
                    .orElse(0L); // Define 0 caso não tenha nenhum resultado

            // Filtra os estados que possuem o número mínimo de partidas
            List<StateWithFewestGamesDTO> stateWithFewestGames = gamesByState.entrySet().stream()
                    .filter(entry -> entry.getValue() == minGames) // Filtra estados com o menor número de partidas
                    .map(entry -> new StateWithFewestGamesDTO(entry.getKey(), entry.getValue().intValue())) // Mapeia para DTO
                    .collect(Collectors.toList()); // Coleta em uma lista de DTOs

            Map<String, Integer> period = new HashMap<>();
            period.put("startYear", finalStartYear);
            period.put("endYear", finalEndYear);

            return new StateWithFewestGamesResponseDTO(period, stateWithFewestGames);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void execute() {

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            matches = lines.skip(1) // Pula o cabeçalho
                    .map(line -> new Match(line.split(","))) // Mapeia para objetos Match
                    .collect(Collectors.toList()); // Coleta em uma lista
        } catch (IOException e) {
            e.printStackTrace();
        }

//        matches.forEach(System.out::println);

//        System.out.println("Quantidade de partidas sem vencedor: " + matchesWithNoWinner.size());

        // print just the matches with the winner equal "-"

//        matchesWithNoWinner.forEach(System.out::println);

        System.out.println("Quantidade de partidas: " + matches.size());

    }

    public static void main(String[] args) {

//        execute();

//        System.out.println(getTeamsWithMostWinsInYear(2023));

//        System.out.println(getStateWithFewestGamesBetweenYears(2023, 2023));

    }

}
