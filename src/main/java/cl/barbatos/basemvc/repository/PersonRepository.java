package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
