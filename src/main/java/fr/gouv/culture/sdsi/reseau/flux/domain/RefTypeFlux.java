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

/**
 * A RefTypeFlux.
 */
@Entity
@Table(name = "ref_type_flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefTypeFlux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "typeFlux")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DemandeFlux> codes = new HashSet<>();
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

    public RefTypeFlux code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefTypeFlux libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<DemandeFlux> getCodes() {
        return codes;
    }

    public RefTypeFlux codes(Set<DemandeFlux> demandeFluxes) {
        this.codes = demandeFluxes;
        return this;
    }

    public RefTypeFlux addCode(DemandeFlux demandeFlux) {
        this.codes.add(demandeFlux);
        demandeFlux.setTypeFlux(this);
        return this;
    }

    public RefTypeFlux removeCode(DemandeFlux demandeFlux) {
        this.codes.remove(demandeFlux);
        demandeFlux.setTypeFlux(null);
        return this;
    }

    public void setCodes(Set<DemandeFlux> demandeFluxes) {
        this.codes = demandeFluxes;
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
        RefTypeFlux refTypeFlux = (RefTypeFlux) o;
        if (refTypeFlux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refTypeFlux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefTypeFlux{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
