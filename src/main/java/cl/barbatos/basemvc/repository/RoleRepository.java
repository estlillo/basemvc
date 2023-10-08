package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

}
