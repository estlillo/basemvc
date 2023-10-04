package cl.barbatos.basemvc.service;

import cl.barbatos.basemvc.model.dto.ContactDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Service
@RequestScope
public class ContactService {

    private int counter = 0;

    public ContactService() {
        System.out.println("ContactService created");
    }

    public boolean saveInfo(ContactDTO contact){
        //TODO: Persist the information in the database
        log.info(contact.toString());
        return true;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
