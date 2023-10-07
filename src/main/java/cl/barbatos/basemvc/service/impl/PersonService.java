package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.model.dto.PersonDTO;
import cl.barbatos.basemvc.model.entity.Person;
import cl.barbatos.basemvc.repository.PersonRepository;
import cl.barbatos.basemvc.service.IPersonService;
import cl.barbatos.basemvc.util.DtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean createUser(PersonDTO person) {
        try {
            personRepository.save(DtoConverter.convertToEntity(person, Person.class));
            return true;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al guardar la persona", ex);
        }
    }

    @Override
    public List<PersonDTO> getAllUsers() {
        List<Person> persons = personRepository.findAll();
        return convertToPersonDTOs(persons);
    }


    private List<PersonDTO> convertToPersonDTOs(List<Person> persons) {
        return persons.stream()
                .map(person -> DtoConverter.convertToDto(person, PersonDTO.class))
                .collect(Collectors.toList());
    }
}
