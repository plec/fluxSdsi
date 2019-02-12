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
 * A RefTypeFonction.
 */
@Entity
@Table(name = "ref_type_fonction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefTypeFonction implements Serializable {

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

    @OneToMany(mappedBy = "codeFonction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefFonction> codes = new HashSet<>();
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

    public RefTypeFonction code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public RefTypeFonction libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<RefFonction> getCodes() {
        return codes;
    }

    public RefTypeFonction codes(Set<RefFonction> refFonctions) {
        this.codes = refFonctions;
        return this;
    }

    public RefTypeFonction addCode(RefFonction refFonction) {
        this.codes.add(refFonction);
        refFonction.setCodeFonction(this);
        return this;
    }

    public RefTypeFonction removeCode(RefFonction refFonction) {
        this.codes.remove(refFonction);
        refFonction.setCodeFonction(null);
        return this;
    }

    public void setCodes(Set<RefFonction> refFonctions) {
        this.codes = refFonctions;
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
        RefTypeFonction refTypeFonction = (RefTypeFonction) o;
        if (refTypeFonction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refTypeFonction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefTypeFonction{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
