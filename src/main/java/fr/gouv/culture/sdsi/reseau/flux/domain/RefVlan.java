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
 * A RefVlan.
 */
@Entity
@Table(name = "ref_vlan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefVlan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "code_zone", nullable = false)
    private String codeZone;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "destVlan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DemandeFlux> codes = new HashSet<>();
    @OneToMany(mappedBy = "sourceVlan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DemandeFlux> codes = new HashSet<>();
    @OneToMany(mappedBy = "destVlan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefMatriceFlux> codes = new HashSet<>();
    @OneToMany(mappedBy = "sourceVlan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefMatriceFlux> codes = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefZone codeZone;

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

    public RefVlan code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeZone() {
        return codeZone;
    }

    public RefVlan codeZone(String codeZone) {
        this.codeZone = codeZone;
        return this;
    }

    public void setCodeZone(String codeZone) {
        this.codeZone = codeZone;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefVlan libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<DemandeFlux> getCodes() {
        return codes;
    }

    public RefVlan codes(Set<DemandeFlux> demandeFluxes) {
        this.codes = demandeFluxes;
        return this;
    }

    public RefVlan addCode(DemandeFlux demandeFlux) {
        this.codes.add(demandeFlux);
        demandeFlux.setDestVlan(this);
        return this;
    }

    public RefVlan removeCode(DemandeFlux demandeFlux) {
        this.codes.remove(demandeFlux);
        demandeFlux.setDestVlan(null);
        return this;
    }

    public void setCodes(Set<DemandeFlux> demandeFluxes) {
        this.codes = demandeFluxes;
    }

    public Set<DemandeFlux> getCodes() {
        return codes;
    }

    public RefVlan codes(Set<DemandeFlux> demandeFluxes) {
        this.codes = demandeFluxes;
        return this;
    }

    public RefVlan addCode(DemandeFlux demandeFlux) {
        this.codes.add(demandeFlux);
        demandeFlux.setSourceVlan(this);
        return this;
    }

    public RefVlan removeCode(DemandeFlux demandeFlux) {
        this.codes.remove(demandeFlux);
        demandeFlux.setSourceVlan(null);
        return this;
    }

    public void setCodes(Set<DemandeFlux> demandeFluxes) {
        this.codes = demandeFluxes;
    }

    public Set<RefMatriceFlux> getCodes() {
        return codes;
    }

    public RefVlan codes(Set<RefMatriceFlux> refMatriceFluxes) {
        this.codes = refMatriceFluxes;
        return this;
    }

    public RefVlan addCode(RefMatriceFlux refMatriceFlux) {
        this.codes.add(refMatriceFlux);
        refMatriceFlux.setDestVlan(this);
        return this;
    }

    public RefVlan removeCode(RefMatriceFlux refMatriceFlux) {
        this.codes.remove(refMatriceFlux);
        refMatriceFlux.setDestVlan(null);
        return this;
    }

    public void setCodes(Set<RefMatriceFlux> refMatriceFluxes) {
        this.codes = refMatriceFluxes;
    }

    public Set<RefMatriceFlux> getCodes() {
        return codes;
    }

    public RefVlan codes(Set<RefMatriceFlux> refMatriceFluxes) {
        this.codes = refMatriceFluxes;
        return this;
    }

    public RefVlan addCode(RefMatriceFlux refMatriceFlux) {
        this.codes.add(refMatriceFlux);
        refMatriceFlux.setSourceVlan(this);
        return this;
    }

    public RefVlan removeCode(RefMatriceFlux refMatriceFlux) {
        this.codes.remove(refMatriceFlux);
        refMatriceFlux.setSourceVlan(null);
        return this;
    }

    public void setCodes(Set<RefMatriceFlux> refMatriceFluxes) {
        this.codes = refMatriceFluxes;
    }

    public RefZone getCodeZone() {
        return codeZone;
    }

    public RefVlan codeZone(RefZone refZone) {
        this.codeZone = refZone;
        return this;
    }

    public void setCodeZone(RefZone refZone) {
        this.codeZone = refZone;
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
        RefVlan refVlan = (RefVlan) o;
        if (refVlan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refVlan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefVlan{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", codeZone='" + getCodeZone() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
