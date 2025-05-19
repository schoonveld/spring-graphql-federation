package dev.s8d.spring_graphql_federation.autorisatieapi.repository;

import dev.s8d.spring_graphql_federation.autorisatieapi.domain.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    List<Credential> findByGebruikerId(Long gebruikerId);
}
