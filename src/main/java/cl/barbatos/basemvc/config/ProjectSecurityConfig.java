package cl.barbatos.basemvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/home", "/features", "/about").permitAll()
                        .requestMatchers("/holidays/**").hasRole("ADMIN")
                        .requestMatchers("/contact/**").authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService users() {



        UserDetails user = User.builder()
                .passwordEncoder((p) -> "{noop}" + p)
                .username("user")
                .roles("USER")
                .password("user123")
                .build();

        UserDetails admin = User.builder()
                .passwordEncoder((p) -> "{noop}" + p)
                .username("admin")
                .password("admin123")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
