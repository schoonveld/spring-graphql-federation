package dev.s8d.springgraphqlfederation.persoonapi.repository;

import dev.s8d.springgraphqlfederation.persoonapi.model.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersoonRepository extends JpaRepository<Persoon, Long>, QueryByExampleExecutor<Persoon> {
}
