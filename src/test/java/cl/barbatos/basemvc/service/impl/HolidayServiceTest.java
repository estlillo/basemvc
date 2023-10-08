package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.exception.NotFoundException;
import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.entity.Holiday;
import cl.barbatos.basemvc.model.enums.Type;
import cl.barbatos.basemvc.repository.HolidayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HolidayServiceTest {

    @InjectMocks
    private HolidayService holidayService;

    @Mock
    private HolidayRepository holidayRepository;

    @Mock
    private HolidayService holidayServiceMock;

    private List<Holiday> mockHolidays;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockHolidays = Arrays.asList(
                new Holiday(1L, LocalDate.of(2023, 1, 1), "Año Nuevo", Type.Festival),
                new Holiday(2L, LocalDate.of(2023, 7, 4), "Día de la Independencia", Type.National),
                new Holiday(3L, LocalDate.of(2023, 12, 25), "Navidad", Type.Religious),
                new Holiday(4L, LocalDate.of(2023, 12, 31), "Año Nuevo", Type.Festival)
                // Agrega más feriados simulados según sea necesario
        );
    }


    @Test
    void getAllHolidays() {
        Mockito.when(holidayRepository.findAll()).thenReturn(mockHolidays);

        // Llama al método de servicio
        List<HolidayDTO> result = holidayService.getAllHolidays();

        // Realiza afirmaciones (assertions) para verificar el resultado
        assertNotNull(result);
        assertEquals(4, result.size()); // Asegúrate de que haya dos feriados simulados en el resultado

    }

    @Test
    void getHolidayById() {

        Long id = 1L;
        Mockito.when(holidayRepository.findById(id)).thenReturn(Optional.ofNullable(mockHolidays.get(0)));
        HolidayDTO result = holidayService.getHolidayById(id);

        assertNotNull(result);

        assertEquals(id, result.getId());

        assertEquals(mockHolidays.get(0).getDay(), result.getDay());

    }
    @Test
    void getHolidayByIdNotFound() {

        Long nonExistentHolidayId = 100L;

        Mockito.when(holidayRepository.findById(nonExistentHolidayId)).thenReturn(Optional.empty());


        Exception exception = assertThrows(NotFoundException.class, () -> {
            holidayService.getHolidayById(nonExistentHolidayId);
        });

        assertEquals("Record with id "+nonExistentHolidayId+" not found", exception.getMessage());

    }

    @Test
    void getHolidaysByDateRange() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        Mockito.when(holidayRepository.findByDayBetween(startDate, endDate)).thenReturn(mockHolidays);

        List<HolidayDTO> result = holidayService.getHolidaysByDateRange(startDate.toString(), endDate.toString());

        assertNotNull(result);

        assertEquals(4, result.size());
    }

    @Test
    void getHolidaysByDateRangeNoResult(){
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        Mockito.when(holidayRepository.findByDayBetween(startDate, endDate)).thenReturn(Collections.emptyList());

        List<HolidayDTO> result = holidayService.getHolidaysByDateRange(startDate.toString(), endDate.toString());

        assertNotNull(result);

        assertEquals(0, result.size());
    }

    @Test
    void getHolidaysByDateRangeNull(){
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        Mockito.when(holidayRepository.findByDayBetween(startDate, endDate)).thenReturn(null);

        List<HolidayDTO> result = holidayService.getHolidaysByDateRange(startDate.toString(), endDate.toString());

        assertNotNull(result);

        assertEquals(0, result.size());
    }
}