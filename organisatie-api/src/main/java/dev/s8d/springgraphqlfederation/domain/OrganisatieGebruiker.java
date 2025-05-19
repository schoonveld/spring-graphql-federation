package dev.s8d.springgraphqlfederation.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class OrganisatieGebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long gebruikerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organisatie_id", referencedColumnName = "id")
    private Organisatie organisatie;
}
