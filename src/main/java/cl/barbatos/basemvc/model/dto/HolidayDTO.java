package cl.barbatos.basemvc.model.dto;

import cl.barbatos.basemvc.model.enums.Type;
import lombok.*;

@Getter
@AllArgsConstructor
public class HolidayDTO {
    private final String day;
    private final String reason;
    private final Type type;
}
