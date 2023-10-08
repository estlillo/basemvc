package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.model.dto.PersonDTO;
import cl.barbatos.basemvc.model.entity.Person;
import cl.barbatos.basemvc.model.entity.Role;
import cl.barbatos.basemvc.repository.AddressRepository;
import cl.barbatos.basemvc.repository.PersonRepository;
import cl.barbatos.basemvc.repository.RoleRepository;
import cl.barbatos.basemvc.service.IPersonService;
import cl.barbatos.basemvc.util.DtoConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean createUser(PersonDTO person) {
        try {

            Person newPerson = DtoConverter.convertToEntity(person, Person.class);

            List<Role> roles = person.getRoles().stream()
                    .map(roleRepository::findByRoleName)
                    .toList();

            newPerson.setRoles(roles);
            newPerson.setPassword(passwordEncoder.encode(person.getPassword()));

            personRepository.save(newPerson);
            return true;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al guardar la persona", ex);
        }
    }


    @Transactional // Asegura una transacción para actualizar la persona y sus roles
    public boolean updateUser(Long userId, PersonDTO updatedPerson) {
        try {
            // Recupera el usuario existente de la base de datos
            Person existingPerson = personRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

            // Actualiza los campos del usuario con la información proporcionada en PersonDTO
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setEmail(updatedPerson.getEmail());
            // ...

            // Recupera la lista de roles actuales del usuario
            List<Role> existingRoles = existingPerson.getRoles();

            // Recupera la lista de roles nuevos del DTO y conviértelos en entidades Role
            List<Role> updatedRoles = updatedPerson.getRoles().stream()
                    .map(roleRepository::findByRoleName)
                    .toList();

            // Determina las diferencias entre las listas de roles
            List<Role> rolesToAdd = updatedRoles.stream()
                    .filter(role -> !existingRoles.contains(role))
                    .toList();

            List<Role> rolesToRemove = existingRoles.stream()
                    .filter(role -> !updatedRoles.contains(role))
                    .toList();

            // Agrega los nuevos roles al usuario y elimina los roles que ya no están presentes
            existingPerson.getRoles().addAll(rolesToAdd);
            existingPerson.getRoles().removeAll(rolesToRemove);

            // Guarda el usuario actualizado en la base de datos
            personRepository.save(existingPerson);

            return true;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al actualizar la persona", ex);
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
