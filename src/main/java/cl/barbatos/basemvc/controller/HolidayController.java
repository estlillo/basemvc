package cl.barbatos.basemvc.controller;

import cl.barbatos.basemvc.service.HolidayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
