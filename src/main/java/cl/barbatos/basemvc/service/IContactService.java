package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.ContactDTO;

public interface IContactService {

    boolean saveInfo(ContactDTO contact);

    boolean updateContactStatus(Long id, boolean status);
}
