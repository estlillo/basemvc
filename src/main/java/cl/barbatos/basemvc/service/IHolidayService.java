package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.dto.filter.HolidayFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHolidayService {


    List<HolidayDTO> getAllHolidays();

    HolidayDTO getHolidayById(Long id);

    List<HolidayDTO> getHolidaysByDateRange(String from, String to);

    Page<HolidayDTO> getAllHolidays(Pageable pageable);

    Page<HolidayDTO> getHolidaysWithFilter(Pageable pageable, HolidayFilterDTO filterDTO);

}
