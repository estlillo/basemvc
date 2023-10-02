package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.dto.HolidayDTO;
import cl.barbatos.basemvc.model.enums.Type;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class HolidayRepository {

    public List<HolidayDTO> findAll() {
        return Arrays.asList(
                new HolidayDTO("1", "2023-01-01", "Año Nuevo", Type.National),
                new HolidayDTO("2", "2023-04-10", "Viernes Santo", Type.Religious),
                new HolidayDTO("3", "2023-04-11", "Sabado Santo", Type.Religious),
                new HolidayDTO("4", "2023-05-01", "Dia del Trabajador", Type.National),
                new HolidayDTO("5", "2023-05-21", "Dia de las Glorias Navales", Type.National),
                new HolidayDTO("6", "2023-06-29", "San Pedro y San Pablo", Type.Religious),
                new HolidayDTO("7", "2023-07-16", "Virgen del Carmen", Type.Religious),
                new HolidayDTO("8", "2023-08-15", "Asuncion de la Virgen", Type.Religious),
                new HolidayDTO("9", "2023-09-18", "Independencia Nacional", Type.National),
                new HolidayDTO("10", "2023-09-19", "Dia de las Glorias del Ejercito", Type.National),
                new HolidayDTO("11", "2023-10-12", "Encuentro de Dos Mundos", Type.National),
                new HolidayDTO("12", "2023-10-31", "Día Nacional de las Iglesias Evangélicas y Protestantes", Type.National),
                new HolidayDTO("13", "2023-11-01", "Dia de Todos los Santos", Type.Religious)
        );
    }

    public HolidayDTO findById(String id) {
        return findAll().stream().filter(holiday -> holiday.getId().equals(id)).findFirst().orElse(null);
    }
}
