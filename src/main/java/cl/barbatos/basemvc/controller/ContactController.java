package cl.barbatos.basemvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping(value = {"/contact"})
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping(value = "/saveInfo")
    public ModelAndView saveInfo(@RequestParam String name, @RequestParam String email, @RequestParam String message){

        log.info("Name: "+ name);
        log.info("Email: "+ email);
        log.info("Message: "+ message);


        return new ModelAndView("redirect:/contact", "message", "Your information has been saved successfully!" );
    }


}
