package dev.s8d.springgraphqlfederation.persoonapi.graphql;

import dev.s8d.springgraphqlfederation.persoonapi.model.Persoon;
import dev.s8d.springgraphqlfederation.persoonapi.repository.PersoonRepository;
import org.springframework.data.domain.Example;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PersoonGraphQLController {

    private final PersoonRepository repository;

    public PersoonGraphQLController(PersoonRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Persoon> searchPersons(@Argument String firstName, @Argument String lastName, @Argument LocalDate dateOfBirth) {
        Persoon probe = Persoon.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .build();
        return repository.findAll(Example.of(probe));
    }

    @QueryMapping
    public Optional<Persoon> findPersonById(@Argument Long id) {
        return repository.findById(id);
    }
}
