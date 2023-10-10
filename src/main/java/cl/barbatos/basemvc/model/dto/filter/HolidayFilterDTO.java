package cl.barbatos.basemvc.model.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayFilterDTO {
    private String from;
    private String to;
    private String reason;
}
