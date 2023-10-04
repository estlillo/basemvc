package cl.barbatos.basemvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/features").setViewName("features");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/contact").setViewName("contact");
    }
}
