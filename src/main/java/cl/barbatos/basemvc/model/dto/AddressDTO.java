package cl.barbatos.basemvc.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

private Long id;
private String street;
private String number;
private String commune;
private String city;
private String country;
private String postalCode;

}
