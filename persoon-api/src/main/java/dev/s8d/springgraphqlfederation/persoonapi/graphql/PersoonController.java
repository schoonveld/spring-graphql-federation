package dev.s8d.springgraphqlfederation.persoonapi.graphql;

import dev.s8d.springgraphqlfederation.persoonapi.model.Persoon;
import dev.s8d.springgraphqlfederation.persoonapi.repository.PersoonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class PersoonController {

    private final PersoonRepository persoonRepository;

    @Autowired
    public PersoonController(PersoonRepository persoonRepository, BatchLoaderRegistry registry) {
        this.persoonRepository = persoonRepository;
        registry.forTypePair(Long.class, Persoon.class)
                .registerBatchLoader((persoonId, environmen) -> {
                        log.debug("Fetching personen with id's [{}]", persoonId.stream()
                                .map(Objects::toString)
                                .collect(Collectors.joining(",")));

                        return Flux.fromIterable(persoonRepository.findAllById(persoonId));
                });
    }

    @EntityMapping
    public List<Persoon> persoon(@Argument List<Long> idList) {
        log.debug("Fetching personen");
        return persoonRepository.findAllById(idList);
    }

//    @EntityMapping
//    public Persoon persoon(@Argument Long id) {
//        log.debug("Fetching Persoon entity with id: {}", id);
//        return persoonRepository.findById(id)
//                .orElseThrow(EntityNotFoundException::new);
//    }

    @QueryMapping
    public List<Persoon> searchPersons(@Argument String firstName, @Argument String lastName, @Argument LocalDate dateOfBirth) {
        log.debug("Query: searchPersons");
        Persoon probe = Persoon.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .build();
        return persoonRepository.findAll(Example.of(probe));
    }

    @QueryMapping
    public Optional<Persoon> findPersonById(@Argument Long id) {
        log.debug("Query: findPersonById for id: {}", id);
        return persoonRepository.findById(id);
    }
}
