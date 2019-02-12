package fr.gouv.culture.sdsi.reseau.flux.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RefZone.
 */
@Entity
@Table(name = "ref_zone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefZone implements Serializable {

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

    @ManyToOne
    @JsonIgnoreProperties("codeZones")
    private RefFonction refFonction;

    @OneToMany(mappedBy = "refZone")
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

    public RefZone code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefZone libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public RefFonction getRefFonction() {
        return refFonction;
    }

    public RefZone refFonction(RefFonction refFonction) {
        this.refFonction = refFonction;
        return this;
    }

    public void setRefFonction(RefFonction refFonction) {
        this.refFonction = refFonction;
    }

    public Set<Flux> getCodes() {
        return codes;
    }

    public RefZone codes(Set<Flux> fluxes) {
        this.codes = fluxes;
        return this;
    }

    public RefZone addCode(Flux flux) {
        this.codes.add(flux);
        flux.setRefZone(this);
        return this;
    }

    public RefZone removeCode(Flux flux) {
        this.codes.remove(flux);
        flux.setRefZone(null);
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
        RefZone refZone = (RefZone) o;
        if (refZone.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refZone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefZone{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
