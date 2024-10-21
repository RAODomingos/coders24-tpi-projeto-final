package dev.dluks.brasileirao.controllers.web;

import dev.dluks.brasileirao.dtos.player.PlayersWithMostCardsResponseDTO;
import dev.dluks.brasileirao.services.PlayersWithMostCardsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebCardController {

    @GetMapping("/web/jogadores/mais-cartoes")
    public String showMostCardsForm(@RequestParam(value = "type", required = false) String type, Model model) {
        if (type != null) {

            PlayersWithMostCardsResponseDTO result = PlayersWithMostCardsService.execute(type);

            if (result == null) {
                model.addAttribute("error", "An error occurred while fetching the data");
                return "mais-cartoes";
            }

            List<Object[]> results = result.jogadoresComMaisCartoes().stream()
                    .map(cartao -> new Object[]{cartao.nome(), cartao.quantidadeCartoes()})
                    .toList();

            model.addAttribute("results", results);
            model.addAttribute("type", type);
        }
        return "mais-cartoes";
    }

}
