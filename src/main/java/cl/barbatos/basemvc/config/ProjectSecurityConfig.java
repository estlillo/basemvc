package cl.barbatos.basemvc.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);


        http.csrf((csrf) -> csrf.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/contact/saveInfo")))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(mvcMatcherBuilder.pattern("/dashboard/**")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/features/**")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/about/**")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/holidays/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/contact/**")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/logout")).authenticated())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
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
