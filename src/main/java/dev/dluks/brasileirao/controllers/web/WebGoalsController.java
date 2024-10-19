package dev.dluks.brasileirao.controllers.web;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostGoalsResponseDTO;
import dev.dluks.brasileirao.services.PlayersWithMostGoalsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebGoalsController {

    @GetMapping("/web/jogadores/mais-gols")
    public String showMostGoalsForm(@RequestParam(value = "type", required = false) String type, Model model) {
        if (type != null) {

            PlayersWithMostGoalsResponseDTO result = PlayersWithMostGoalsService.execute(type);

            if (result == null) {
                model.addAttribute("error", "An error occurred while fetching the data");
                return "most-goals";
            }

            List<Object[]> results = result.jogadores().stream()
                    .map(jogador -> new Object[]{jogador.nome(), jogador.gols()})
                    .toList();

            model.addAttribute("results", results);
            model.addAttribute("type", type);
        }
        return "most-goals"; // Renderiza a mesma página com ou sem resultados
    }

}