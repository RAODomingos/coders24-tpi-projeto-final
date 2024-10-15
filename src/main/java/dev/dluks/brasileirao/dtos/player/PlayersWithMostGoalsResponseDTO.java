package dev.dluks.brasileirao.dtos.player;

import java.util.List;

public class PlayersWithMostGoalsResponseDTO {
    private List<PlayerWithMostGoals> jogadores;

     public PlayersWithMostGoalsResponseDTO(List<PlayerWithMostGoals> jogadores) {
          this.jogadores = jogadores;

     }


     public List<PlayerWithMostGoals> getJogadores() {
          return jogadores;
     }
}
