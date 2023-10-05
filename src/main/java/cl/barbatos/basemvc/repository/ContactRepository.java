package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMessage(ContactDTO contact){
        String sql = "INSERT INTO CONTACT_MSG (NAME,EMAIL,MESSAGE," +
                "CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getName(),
                contact.getEmail(),contact.getMessage(),contact.getCreatedAt(),contact.getCreatedBy());
    }

}
