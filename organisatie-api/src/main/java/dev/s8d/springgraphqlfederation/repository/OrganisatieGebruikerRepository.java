package dev.s8d.springgraphqlfederation.repository;

import dev.s8d.springgraphqlfederation.domain.Organisatie;
import dev.s8d.springgraphqlfederation.domain.OrganisatieGebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisatieGebruikerRepository extends JpaRepository<OrganisatieGebruiker, Long> {
    List<OrganisatieGebruiker> findByOrganisatie(Organisatie organisatie);
    List<OrganisatieGebruiker> findByGebruikerId(Long id);
}
