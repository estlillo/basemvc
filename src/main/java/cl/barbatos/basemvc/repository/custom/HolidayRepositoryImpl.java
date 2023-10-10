package cl.barbatos.basemvc.repository.custom;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.dto.filter.HolidayFilterDTO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public class HolidayRepositoryImpl implements HolidayRepositoryCustom{

    @Autowired
    private EntityManager entityManager;

    @Override

    public Page<HolidayDTO> getHolidaysWithFilter(Pageable pageable, HolidayFilterDTO filterDTO) {
        return null;

    }
}
