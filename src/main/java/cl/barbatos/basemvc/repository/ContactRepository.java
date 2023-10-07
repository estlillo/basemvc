package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.dto.ContactDTO;
import cl.barbatos.basemvc.model.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
