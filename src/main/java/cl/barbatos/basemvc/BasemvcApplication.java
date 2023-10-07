package cl.barbatos.basemvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"cl.barbatos.basemvc.repository"})
@EntityScan({"cl.barbatos.basemvc.model.entity"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class BasemvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasemvcApplication.class, args);
	}

}
