package cl.barbatos.basemvc.controller;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class HolidayController {

   private HolidayService holidayService;

   public HolidayController(HolidayService holidayService) {
       this.holidayService = holidayService;
   }

    @GetMapping("/holidays")
    public String getAllHolidays(Model model) {
        model.addAttribute("holidays", holidayService.getAllHolidays());
        return "holidays.html";
    }

    @GetMapping("/holidaysByDate")
    public String getHolidaysByDateRange(@RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to, Model model) {

        model.addAttribute("holidaysResult", holidayService.getHolidaysByDateRange(from, to));
        return "holidays.html";
    }

    @GetMapping("/holidays/{id}")
    public String getHolidayById(@PathVariable String id, Model model) {

        HolidayDTO holiday = holidayService.getHolidayById(id);
        model.addAttribute("holidaysResult", holidayService.getAllHolidays());
        model.addAttribute("holidaySelected",  holiday);
        return "holidays.html";
    }
}
