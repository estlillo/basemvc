package cl.barbatos.basemvc.repository.custom;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.dto.filter.HolidayFilterDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface HolidayRepositoryCustom {
    Page<HolidayDTO> getHolidaysWithFilter(Pageable pageable, HolidayFilterDTO filterDTO);
}
