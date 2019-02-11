package fr.gouv.culture.sdsi.reseau.flux.repository;

import fr.gouv.culture.sdsi.reseau.flux.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
