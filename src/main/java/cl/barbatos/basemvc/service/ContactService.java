package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.ContactDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    public boolean saveInfo(ContactDTO contact){
        //TODO: Persist the information in the database
        log.info(contact.toString());
        return true;
    }

}
