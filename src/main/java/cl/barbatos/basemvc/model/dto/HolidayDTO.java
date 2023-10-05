package cl.barbatos.basemvc.model.dto;

import cl.barbatos.basemvc.model.enums.Type;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class HolidayDTO implements Serializable {
    private Long id;
    private LocalDate day;
    private String reason;
    private Type type;
}
