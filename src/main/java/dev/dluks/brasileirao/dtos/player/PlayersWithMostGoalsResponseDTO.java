package dev.dluks.brasileirao.dtos.player;

import java.util.List;

public class PlayersWithMostGoalsResponseDTO {
    private String type;
    private List<PlayerWithMostGoals> jogadores;

     public PlayersWithMostGoalsResponseDTO(String type, List<PlayerWithMostGoals> jogadores) {
         this.type = type;
         this.jogadores = jogadores;

     }

    public String getType() {
        return type;
    }

     public List<PlayerWithMostGoals> getJogadores() {
          return jogadores;
     }

}
