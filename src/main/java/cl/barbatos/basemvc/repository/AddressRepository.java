package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
