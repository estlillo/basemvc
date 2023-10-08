package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.model.dto.PersonDTO;
import cl.barbatos.basemvc.model.dto.RoleDTO;
import cl.barbatos.basemvc.model.entity.Person;
import cl.barbatos.basemvc.model.entity.Role;
import cl.barbatos.basemvc.repository.AddressRepository;
import cl.barbatos.basemvc.repository.PersonRepository;
import cl.barbatos.basemvc.repository.RoleRepository;
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

    private final RoleRepository roleRepository;

    private final AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public boolean createUser(PersonDTO person) {
        try {

            Person newPerson = DtoConverter.convertToEntity(person, Person.class);

            List<Role> roles = person.getRoles().stream()
                    .map(roleRepository::findByRoleName)
                    .toList();

            newPerson.setRoles(roles);

            personRepository.save(newPerson);
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