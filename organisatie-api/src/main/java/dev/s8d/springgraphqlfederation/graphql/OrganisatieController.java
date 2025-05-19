package dev.s8d.springgraphqlfederation.graphql;

import dev.s8d.springgraphqlfederation.domain.Organisatie;
import dev.s8d.springgraphqlfederation.repository.OrganisatieGebruikerRepository;
import dev.s8d.springgraphqlfederation.repository.OrganisatieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrganisatieController {

    private final OrganisatieRepository organisatieRepository;

    private final OrganisatieGebruikerRepository organisatieGebruikerRepository;

    @QueryMapping
    public Organisatie organisatieById(@Argument Long organisatieId) {
        return organisatieRepository.findById(organisatieId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @EntityMapping
    public List<Persoon> persoon(@Argument List<Long> idList) {
        log.debug("Fetching Persoon entity for id: {}", idList.stream()
                .map(Objects::toString).collect(Collectors.joining(",")));

        return idList.stream()
                .map(Persoon::new).toList();
    }

    @BatchMapping(typeName = "Persoon", field = "organisatie")
    public Map<Persoon, Organisatie> organisaties(List<Persoon> personen) {
        log.debug("Fetching organisaties for personen with id's: [{}]", personen.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(",")));

        return personen.stream()
                .collect(Collectors.toMap(persoon -> persoon,
                        persoon -> organisatieGebruikerRepository.findByGebruikerId(persoon.id()).getFirst().getOrganisatie()));
    }

    @BatchMapping
    public Map<Organisatie, List<Persoon>> gebruikers(List<Organisatie> organisaties) {
        log.debug("Fetching organisatieGebruikers for organisaties with id's: {}",
                organisaties.stream()
                        .map(organisatie -> organisatie.getId().toString())
                        .collect(Collectors.joining(","))
        );

        return organisaties.stream().collect(Collectors.toMap(
                organisatie -> organisatie,
                organisatie -> organisatieGebruikerRepository.findByOrganisatie(organisatie).stream()
                            .map(organisatieGebruiker -> new Persoon(organisatieGebruiker.getGebruikerId()))
                            .toList()

        ));
    }
}
