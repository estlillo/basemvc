package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.model.dto.ContactDTO;
import cl.barbatos.basemvc.model.entity.Contact;
import cl.barbatos.basemvc.repository.ContactRepository;
import cl.barbatos.basemvc.service.IContactService;
import cl.barbatos.basemvc.util.DtoConverter;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public boolean saveInfo(ContactDTO contact) {
        Contact newContact = DtoConverter.convertToEntity(contact, Contact.class);
        try {
            newContact.setStatus(true);
            contactRepository.save(newContact);
            return true;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al guardar el contacto", ex);
        }
    }

    @Override
    public boolean updateContactStatus(Long id, boolean status) {
        try {
            Contact contact = contactRepository.findById(id).
                    orElseThrow(() -> new EntityNotFoundException("Contacto no encontrado"));

            contact.setStatus(status);
            contactRepository.save(contact);

            return true;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al actualizar el contacto", ex);
        }
    }

}
