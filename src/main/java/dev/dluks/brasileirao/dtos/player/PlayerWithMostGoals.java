package dev.dluks.brasileirao.dtos.player;

public class PlayerWithMostGoals {
    private  String nome;
    private Integer gols;

    public PlayerWithMostGoals(String nome, Integer gols) {
        this.nome = nome;
        this.gols = gols;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getGols() {
        return gols;
    }

    public void setGols(Integer gols) {
        this.gols = gols;
    }
}
