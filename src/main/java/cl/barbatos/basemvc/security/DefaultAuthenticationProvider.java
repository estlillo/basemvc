package cl.barbatos.basemvc.security;

import cl.barbatos.basemvc.model.entity.Person;
import cl.barbatos.basemvc.model.entity.Role;
import cl.barbatos.basemvc.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepository.findByUsername(username);

        if (person != null && isPasswordMatches(password, person.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(person.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::getRoleName)
                .toList();
    }

    @Profile("!prod")
    private boolean isPasswordMatches(String password, String savedPassword) {
        return passwordEncoder.matches(password, savedPassword);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
