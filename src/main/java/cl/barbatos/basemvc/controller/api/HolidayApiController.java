package cl.barbatos.basemvc.controller.api;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.dto.filter.HolidayFilterDTO;
import cl.barbatos.basemvc.service.IHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayApiController {


    private final IHolidayService holidayService;

    @Autowired
    public HolidayApiController(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public Page<HolidayDTO> getAllHolidays(Pageable pageable, @RequestParam HolidayFilterDTO filterDTO) {
        return holidayService.getAllHolidays(pageable);
    }

}
