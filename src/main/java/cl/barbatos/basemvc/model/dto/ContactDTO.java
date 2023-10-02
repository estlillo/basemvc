package cl.barbatos.basemvc.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDTO {

    private String name;
    private String email;
    private String message;

}
