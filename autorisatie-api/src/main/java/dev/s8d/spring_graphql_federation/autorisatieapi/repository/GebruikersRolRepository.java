package dev.s8d.spring_graphql_federation.autorisatieapi.repository;

import dev.s8d.spring_graphql_federation.autorisatieapi.domain.GebruikersRol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GebruikersRolRepository extends JpaRepository<GebruikersRol, Long> {
    List<GebruikersRol> findByGebruikerId(Long gebruikerId);
}
