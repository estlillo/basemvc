package cl.barbatos.basemvc.controller;

import cl.barbatos.basemvc.model.dto.PersonDTO;
import cl.barbatos.basemvc.model.dto.RoleDTO;
import cl.barbatos.basemvc.service.IPersonService;
import cl.barbatos.basemvc.service.IRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {

    private final IPersonService personService;

    private final IRoleService roleService;

    @Autowired
    public PublicController(IPersonService personService, IRoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String displayRegisterPage(Model model) {

        List<RoleDTO> roles = roleService.getAllRoles();
        model.addAttribute("person", new PersonDTO());
        model.addAttribute("availableRoles", roles);
        return "register.html";
    }

    @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("person") PersonDTO personDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {

            List<RoleDTO> roles = roleService.getAllRoles();
            model.addAttribute("availableRoles", roles);
            return "register.html";
        }

        log.info("Person: " + personDTO.toString());

        if(personService.createUser(personDTO)){
            log.info("Person saved successfully");
        }

        return "redirect:/login?register=true";
    }
}
