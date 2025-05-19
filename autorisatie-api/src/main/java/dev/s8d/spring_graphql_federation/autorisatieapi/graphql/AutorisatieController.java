package dev.s8d.spring_graphql_federation.autorisatieapi.graphql;

import dev.s8d.spring_graphql_federation.autorisatieapi.domain.Credential;
import dev.s8d.spring_graphql_federation.autorisatieapi.domain.GebruikersRol;
import dev.s8d.spring_graphql_federation.autorisatieapi.domain.Rol;
import dev.s8d.spring_graphql_federation.autorisatieapi.repository.CredentialRepository;
import dev.s8d.spring_graphql_federation.autorisatieapi.repository.GebruikersRolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AutorisatieController {

    private final GebruikersRolRepository gebruikersRolRepository;
    private final CredentialRepository credentialRepository;

    @EntityMapping
    public Persoon persoon(@Argument Long id) {
        log.debug("Fetching Persoon entity for id: {}", id);
        return new Persoon(id);
    }

    @BatchMapping
    public Map<Persoon, List<Rol>> rollen(List<Persoon> personen) {
        log.debug("Batch fetching rollen voor persoon met id's: {}", personen.stream()
                        .map(persoon -> persoon.id().toString())
                        .collect(Collectors.joining(",")));

        return personen.stream()
                .collect(Collectors.toMap(persoon -> persoon,
                        persoon -> gebruikersRolRepository.findByGebruikerId(persoon.id())
                                .stream().map(GebruikersRol::getRol)
                                .toList()));
    }

    @BatchMapping
    public Map<Persoon, List<Credential>> credentials(List<Persoon> personen) {
        log.debug("Batch fetching credentials voor persoon met id's: {}", personen.stream()
                        .map(persoon -> persoon.id().toString())
                        .collect(Collectors.joining(",")));

        return personen.stream()
                .collect(Collectors.toMap(persoon -> persoon,
                        persoon -> credentialRepository.findByGebruikerId(persoon.id())));
    }
}
