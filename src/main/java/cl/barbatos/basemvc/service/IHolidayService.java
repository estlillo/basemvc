package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.HolidayDTO;

import java.util.List;

public interface IHolidayService {


    List<HolidayDTO> getAllHolidays();

    HolidayDTO getHolidayById(Long id);

    List<HolidayDTO> getHolidaysByDateRange(String from, String to);

}
