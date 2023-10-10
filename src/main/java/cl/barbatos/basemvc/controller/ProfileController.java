package cl.barbatos.basemvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {

    public ModelAndView displayMessages(Model model){
        ModelAndView messagesPage = new ModelAndView("profile.html");
        messagesPage.setViewName("messages");
        messagesPage.addObject("messages", "messages");
        return messagesPage;
    }

}
