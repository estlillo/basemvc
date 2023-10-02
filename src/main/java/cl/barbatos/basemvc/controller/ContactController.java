package cl.barbatos.basemvc.controller;

import cl.barbatos.basemvc.model.dto.ContactDTO;
import cl.barbatos.basemvc.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = {"/contact"})
    public String displayContactPage() {
        return "contact.html";
    }

    //request params example
    @PostMapping(value = "/saveInfoRequestParams")
    public ModelAndView saveInfoRequestParam(@RequestParam String name, @RequestParam String email, @RequestParam String message) {

        log.info("Name: " + name);
        log.info("Email: " + email);
        log.info("Message: " + message);


        return new ModelAndView("redirect:/contact", "message", "Your information has been saved successfully!");
    }

    @PostMapping(value = "/saveInfo")
    public ModelAndView saveInfo(ContactDTO contact) {
        contactService.saveInfo(contact);
        return new ModelAndView("redirect:/contact", "message", "Your information has been saved successfully!");
    }


}
