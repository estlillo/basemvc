package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.PersonDTO;

import java.util.List;

public interface IPersonService {

    boolean createUser(PersonDTO person);

    List<PersonDTO> getAllUsers();

}
