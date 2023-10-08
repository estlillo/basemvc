package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void save() {
        //arrange
        Role rolStudent = Role.builder().roleName(Role.ROLE_STUDENT).build();

        //act
        Role savedRole = roleRepository.save(rolStudent);

        //assert
        Assertions.assertNotNull(savedRole);
        Assertions.assertNotNull(savedRole.getId());
        Assertions.assertEquals(rolStudent.getRoleName(), savedRole.getRoleName());
    }

}
