package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.entity.Holiday;
import cl.barbatos.basemvc.model.enums.Type;
import cl.barbatos.basemvc.repository.HolidayRepository;
import cl.barbatos.basemvc.service.impl.HolidayService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HolidayServiceTest {
    @InjectMocks
    private HolidayService holidayService;

    @Mock
    private HolidayRepository holidayRepository;

    public HolidayServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllHolidays() {
        // Configura el comportamiento simulado del repositorio
        List<Holiday> mockHolidays = Arrays.asList(
                new Holiday(1L, LocalDate.of(2023, 1, 1), "Año Nuevo", Type.Festival),
                new Holiday(2L, LocalDate.of(2023, 7, 4), "Día de la Independencia", Type.National)
                // Agrega más feriados simulados según sea necesario
        );
        Mockito.when(holidayRepository.findAll()).thenReturn(mockHolidays);

        // Llama al método de servicio
        List<HolidayDTO> result = holidayService.getAllHolidays();

        // Realiza afirmaciones (assertions) para verificar el resultado
        assertNotNull(result);
        assertEquals(2, result.size()); // Asegúrate de que haya dos feriados simulados en el resultado

    }
}