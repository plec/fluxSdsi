package fr.gouv.culture.sdsi.reseau.flux.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import fr.gouv.culture.sdsi.reseau.flux.domain.enumeration.TypeFlux;

/**
 * A RefFlux.
 */
@Entity
@Table(name = "ref_flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefFlux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type", nullable = false)
    private TypeFlux type;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "refFlux")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Flux> codes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public RefFlux code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeFlux getType() {
        return type;
    }

    public RefFlux type(TypeFlux type) {
        this.type = type;
        return this;
    }

    public void setType(TypeFlux type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefFlux libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Flux> getCodes() {
        return codes;
    }

    public RefFlux codes(Set<Flux> fluxes) {
        this.codes = fluxes;
        return this;
    }

    public RefFlux addCode(Flux flux) {
        this.codes.add(flux);
        flux.setRefFlux(this);
        return this;
    }

    public RefFlux removeCode(Flux flux) {
        this.codes.remove(flux);
        flux.setRefFlux(null);
        return this;
    }

    public void setCodes(Set<Flux> fluxes) {
        this.codes = fluxes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefFlux refFlux = (RefFlux) o;
        if (refFlux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refFlux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefFlux{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", type='" + getType() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
