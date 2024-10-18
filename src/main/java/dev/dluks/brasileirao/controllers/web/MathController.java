package dev.dluks.brasileirao.controllers.web;

import dev.dluks.brasileirao.dtos.team.TeamsWithMostWinsInResponseDTO;
import dev.dluks.brasileirao.services.TeamsWithMostWinsInYearService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MathController {

    @GetMapping("/most-wins")
    public String showMostWinsForm(@RequestParam(value = "year", required = false) String year, Model model) {
        if (year != null) {

            TeamsWithMostWinsInResponseDTO result = TeamsWithMostWinsInYearService.execute(year);

            if (result == null) {
                model.addAttribute("error", "An error occurred while fetching the data");
                return "most-wins-form";
            }

            List<Object[]> results = result.times().stream()
                    .map(team -> new Object[]{team.nome(), team.vitorias()})
                    .toList();

            model.addAttribute("results", results);
            model.addAttribute("selectedYear", year);
        }
        return "most-wins-form"; // Renderiza a mesma página com ou sem resultados
    }

}
