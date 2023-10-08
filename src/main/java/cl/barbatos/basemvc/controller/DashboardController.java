package cl.barbatos.basemvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DashboardController {

    @RequestMapping(value = "/dashboard")
    public String displayDashboardPage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());

        List<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toList());

        log.info("Roles: {}", roles);

        model.addAttribute("roles", roles.toString());

        return "dashboard.html";
    }
}
