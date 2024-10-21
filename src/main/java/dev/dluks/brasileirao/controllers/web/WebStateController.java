package dev.dluks.brasileirao.controllers.web;

import dev.dluks.brasileirao.dtos.state.StatesWithFewestGamesResponseDTO;
import dev.dluks.brasileirao.services.StatesWithFewestGamesBetweenYearsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebStateController {

    @GetMapping("/web/estados/menos-jogos")
    public String showMostWinsForm(
            @RequestParam(name = "anoInicio", required = false) String startYear,
            @RequestParam(name = "anoFim", required = false) String endYear,
            Model model) {

        if (startYear != null && endYear != null) {
            StatesWithFewestGamesResponseDTO result =
                    StatesWithFewestGamesBetweenYearsService.execute(startYear, endYear);

            if (result == null) {
                model.addAttribute("error", "An error occurred while fetching the data");
                return "menos-jogos";
            }

            List<Object[]> results = result.estados().stream()
                    .map(estado -> new Object[]{estado.nome(), estado.jogos()})
                    .toList();

            model.addAttribute("results", results);
            model.addAttribute("selectedStartYear", startYear);
            model.addAttribute("selectedEndYear", endYear);
        }
        return "menos-jogos";
    }

}
