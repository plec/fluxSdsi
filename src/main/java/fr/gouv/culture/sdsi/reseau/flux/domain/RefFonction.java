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
 * A RefFonction.
 */
@Entity
@Table(name = "ref_fonction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefFonction implements Serializable {

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
    @Column(name = "code_fonction", nullable = false)
    private String codeFonction;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefTypeFonction refTypeFonction;

    @OneToMany(mappedBy = "refFonction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefZone> codeZones = new HashSet<>();
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

    public RefFonction code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeZone() {
        return codeZone;
    }

    public RefFonction codeZone(String codeZone) {
        this.codeZone = codeZone;
        return this;
    }

    public void setCodeZone(String codeZone) {
        this.codeZone = codeZone;
    }

    public String getCodeFonction() {
        return codeFonction;
    }

    public RefFonction codeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
        return this;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefFonction libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public RefTypeFonction getRefTypeFonction() {
        return refTypeFonction;
    }

    public RefFonction refTypeFonction(RefTypeFonction refTypeFonction) {
        this.refTypeFonction = refTypeFonction;
        return this;
    }

    public void setRefTypeFonction(RefTypeFonction refTypeFonction) {
        this.refTypeFonction = refTypeFonction;
    }

    public Set<RefZone> getCodeZones() {
        return codeZones;
    }

    public RefFonction codeZones(Set<RefZone> refZones) {
        this.codeZones = refZones;
        return this;
    }

    public RefFonction addCodeZone(RefZone refZone) {
        this.codeZones.add(refZone);
        refZone.setRefFonction(this);
        return this;
    }

    public RefFonction removeCodeZone(RefZone refZone) {
        this.codeZones.remove(refZone);
        refZone.setRefFonction(null);
        return this;
    }

    public void setCodeZones(Set<RefZone> refZones) {
        this.codeZones = refZones;
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
        RefFonction refFonction = (RefFonction) o;
        if (refFonction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refFonction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefFonction{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", codeZone='" + getCodeZone() + "'" +
            ", codeFonction='" + getCodeFonction() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
