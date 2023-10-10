package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.entity.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    List<Holiday> findByDayBetween(LocalDate startDate, LocalDate endDate);
    //pageable
    Page<Holiday> findByDayBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);



}
