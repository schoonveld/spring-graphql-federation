package dev.s8d.spring_graphql_federation.autorisatieapi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long gebruikerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CredentialType type;
}
