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
 * A RefTypeAuthorisation.
 */
@Entity
@Table(name = "ref_type_authorisation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefTypeAuthorisation implements Serializable {

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

    @OneToMany(mappedBy = "typeAuthorisation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefMatriceFlux> codes = new HashSet<>();
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

    public RefTypeAuthorisation code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefTypeAuthorisation libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<RefMatriceFlux> getCodes() {
        return codes;
    }

    public RefTypeAuthorisation codes(Set<RefMatriceFlux> refMatriceFluxes) {
        this.codes = refMatriceFluxes;
        return this;
    }

    public RefTypeAuthorisation addCode(RefMatriceFlux refMatriceFlux) {
        this.codes.add(refMatriceFlux);
        refMatriceFlux.setTypeAuthorisation(this);
        return this;
    }

    public RefTypeAuthorisation removeCode(RefMatriceFlux refMatriceFlux) {
        this.codes.remove(refMatriceFlux);
        refMatriceFlux.setTypeAuthorisation(null);
        return this;
    }

    public void setCodes(Set<RefMatriceFlux> refMatriceFluxes) {
        this.codes = refMatriceFluxes;
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
        RefTypeAuthorisation refTypeAuthorisation = (RefTypeAuthorisation) o;
        if (refTypeAuthorisation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refTypeAuthorisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefTypeAuthorisation{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
