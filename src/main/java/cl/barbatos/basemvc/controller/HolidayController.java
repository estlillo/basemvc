package cl.barbatos.basemvc.controller;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HolidayController {

    @GetMapping("/holidays")
    public String getAllHolidays(Model model) {

        List<HolidayDTO> holidays = Arrays.asList(
                new HolidayDTO("2020-01-01", "Año Nuevo", Type.National),
                new HolidayDTO("2020-04-10", "Viernes Santo", Type.Religious)
        );

        model.addAttribute("holidays", holidays);

        return "holidays.html";
    }
}
