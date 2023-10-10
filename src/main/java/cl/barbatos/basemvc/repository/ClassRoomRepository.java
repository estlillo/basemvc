package cl.barbatos.basemvc.repository;

import cl.barbatos.basemvc.model.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
