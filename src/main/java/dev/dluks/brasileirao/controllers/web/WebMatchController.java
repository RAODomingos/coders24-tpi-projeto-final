package dev.dluks.brasileirao.controllers.web;

import dev.dluks.brasileirao.dtos.game.MatchsWithHighestScoreResponseDTO;
import dev.dluks.brasileirao.services.MatchsWithHighestScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebMatchController {

    @GetMapping("/web/jogos/maior-placar")
    public String showMostWinsForm(@RequestParam(value = "year", required = false) String year, Model model) {
        if (year != null) {

            MatchsWithHighestScoreResponseDTO result = MatchsWithHighestScoreService.execute(year);

            if (result == null) {
                model.addAttribute("error", "An error occurred while fetching the data");
                return "maiorplacar";
            }

            List<Object[]> results = result.partidasComMaiorPlacar().stream()
                    .map(team -> new Object[]{
                            team.rodada(),
                            team.timeCasa(),
                            team.timeVisitante(),
                            team.ganhador(),
                            team.estadio(),
                            team.placarTimeCasa(),
                            team.placarTimeVisitante(),
                            team.placarTotal()})
                    .toList();

            model.addAttribute("results", results);
            model.addAttribute("selectedYear", year);
        }
        return "maiorplacar";
    }

}
