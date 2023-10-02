package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.repository.HolidayRepository;
import cl.barbatos.basemvc.util.ComparatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<HolidayDTO> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public List<HolidayDTO> getHolidaysByDateRange(String from, String to) {
        List<HolidayDTO> holidays = getAllHolidays();
        if (ComparatorUtil.isNullOrEmpty(from) || ComparatorUtil.isNullOrEmpty(to)) {
            return holidays;
        }
        return holidays.stream().filter(holiday -> holiday.getDay().compareTo(from) >= 0 && holiday.getDay().compareTo(to) <= 0).toList();
    }

    public HolidayDTO getHolidayById(String id) {
        return holidayRepository.findById(id);
    }
}
