package cl.barbatos.basemvc.controller.api;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.dto.filter.HolidayFilterDTO;
import cl.barbatos.basemvc.service.IHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayRestController {


    private final IHolidayService holidayService;

    @Autowired
    public HolidayRestController(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public Page<HolidayDTO> getAllHolidays(Pageable pageable) {
        log.info("info de prueba");
        log.debug("debug de prueba");
        log.error("error de prueba");

        return holidayService.getAllHolidays(pageable);
    }

}
