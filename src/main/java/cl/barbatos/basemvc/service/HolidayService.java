package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.exception.NotFoundException;
import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.entity.Holiday;
import cl.barbatos.basemvc.repository.HolidayRepository;
import cl.barbatos.basemvc.util.ComparatorUtil;
import cl.barbatos.basemvc.util.DtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<HolidayDTO> getAllHolidays() {

        List<Holiday> holidaysList = holidayRepository.findAll();

        return convertToHolidayDTOs(holidaysList);
    }

    public HolidayDTO getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return DtoConverter.convertToDto(holiday, HolidayDTO.class);
    }

    public List<HolidayDTO> getHolidaysByDateRange(String from, String to) {
        if (ComparatorUtil.isDateValid(from) && ComparatorUtil.isDateValid(to)) {
            LocalDate startDate = LocalDate.parse(from);
            LocalDate endDate = LocalDate.parse(to);
            return convertToHolidayDTOs(holidayRepository.findByDayBetween(startDate, endDate));
        } else {
            return getAllHolidays();
        }

    }

    private List<HolidayDTO> convertToHolidayDTOs(List<Holiday> holidays) {
        return holidays.stream()
                .map(holiday -> DtoConverter.convertToDto(holiday, HolidayDTO.class))
                .collect(Collectors.toList());
    }
}
