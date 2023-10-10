package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.exception.NotFoundException;
import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.dto.filter.HolidayFilterDTO;
import cl.barbatos.basemvc.model.entity.Holiday;
import cl.barbatos.basemvc.repository.HolidayRepository;
import cl.barbatos.basemvc.service.IHolidayService;
import cl.barbatos.basemvc.util.ComparatorUtil;
import cl.barbatos.basemvc.util.DtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HolidayService implements IHolidayService {

    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<HolidayDTO> getAllHolidays() {

        List<Holiday> holidaysList = holidayRepository.findAll();

        return convertToHolidayDTOs(holidaysList);
    }

    @Override
    public HolidayDTO getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return DtoConverter.convertToDto(holiday, HolidayDTO.class);
    }

    @Override
    public List<HolidayDTO> getHolidaysByDateRange(String from, String to) {
        if (ComparatorUtil.isDateValid(from) && ComparatorUtil.isDateValid(to)) {
            LocalDate startDate = LocalDate.parse(from);
            LocalDate endDate = LocalDate.parse(to);

            List<Holiday> holidaysList = holidayRepository.findByDayBetween(startDate, endDate);

            if (holidaysList == null) {
                holidaysList = Collections.emptyList();
            }

            return convertToHolidayDTOs(holidaysList);
        } else {
            return getAllHolidays();
        }

    }

    @Override
    public Page<HolidayDTO> getAllHolidays(Pageable pageable) {
        Page<Holiday> holidaysPage = holidayRepository.findAll(pageable);
        return holidaysPage.map(holiday -> DtoConverter.convertToDto(holiday, HolidayDTO.class));
    }

    @Override
    public Page<HolidayDTO> getHolidaysWithFilter(Pageable pageable, HolidayFilterDTO filterDTO) {
          return null;
    }

    private List<HolidayDTO> convertToHolidayDTOs(List<Holiday> holidays) {
        return holidays.stream()
                .filter(Objects::nonNull)
                .map(holiday -> DtoConverter.convertToDto(holiday, HolidayDTO.class))
                .collect(Collectors.toList());
    }
}
