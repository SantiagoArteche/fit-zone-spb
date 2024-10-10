package ar.fit_zone_spboot.repository;

import ar.fit_zone_spboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
