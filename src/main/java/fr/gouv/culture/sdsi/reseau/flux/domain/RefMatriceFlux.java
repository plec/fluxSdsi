package fr.gouv.culture.sdsi.reseau.flux.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RefMatriceFlux.
 */
@Entity
@Table(name = "ref_matrice_flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefMatriceFlux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "environnement", nullable = false)
    private String environnement;

    @NotNull
    @Column(name = "type_flux", nullable = false)
    private String typeFlux;

    @NotNull
    @Column(name = "source_vlan", nullable = false)
    private String sourceVlan;

    @NotNull
    @Column(name = "port", nullable = false)
    private String port;

    @NotNull
    @Column(name = "dest_vlan", nullable = false)
    private String destVlan;

    @NotNull
    @Column(name = "type_authorisation", nullable = false)
    private String typeAuthorisation;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefVlan destVlan;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefVlan sourceVlan;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefTypeAuthorisation typeAuthorisation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnvironnement() {
        return environnement;
    }

    public RefMatriceFlux environnement(String environnement) {
        this.environnement = environnement;
        return this;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public String getTypeFlux() {
        return typeFlux;
    }

    public RefMatriceFlux typeFlux(String typeFlux) {
        this.typeFlux = typeFlux;
        return this;
    }

    public void setTypeFlux(String typeFlux) {
        this.typeFlux = typeFlux;
    }

    public String getSourceVlan() {
        return sourceVlan;
    }

    public RefMatriceFlux sourceVlan(String sourceVlan) {
        this.sourceVlan = sourceVlan;
        return this;
    }

    public void setSourceVlan(String sourceVlan) {
        this.sourceVlan = sourceVlan;
    }

    public String getPort() {
        return port;
    }

    public RefMatriceFlux port(String port) {
        this.port = port;
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDestVlan() {
        return destVlan;
    }

    public RefMatriceFlux destVlan(String destVlan) {
        this.destVlan = destVlan;
        return this;
    }

    public void setDestVlan(String destVlan) {
        this.destVlan = destVlan;
    }

    public String getTypeAuthorisation() {
        return typeAuthorisation;
    }

    public RefMatriceFlux typeAuthorisation(String typeAuthorisation) {
        this.typeAuthorisation = typeAuthorisation;
        return this;
    }

    public void setTypeAuthorisation(String typeAuthorisation) {
        this.typeAuthorisation = typeAuthorisation;
    }

    public RefVlan getDestVlan() {
        return destVlan;
    }

    public RefMatriceFlux destVlan(RefVlan refVlan) {
        this.destVlan = refVlan;
        return this;
    }

    public void setDestVlan(RefVlan refVlan) {
        this.destVlan = refVlan;
    }

    public RefVlan getSourceVlan() {
        return sourceVlan;
    }

    public RefMatriceFlux sourceVlan(RefVlan refVlan) {
        this.sourceVlan = refVlan;
        return this;
    }

    public void setSourceVlan(RefVlan refVlan) {
        this.sourceVlan = refVlan;
    }

    public RefTypeAuthorisation getTypeAuthorisation() {
        return typeAuthorisation;
    }

    public RefMatriceFlux typeAuthorisation(RefTypeAuthorisation refTypeAuthorisation) {
        this.typeAuthorisation = refTypeAuthorisation;
        return this;
    }

    public void setTypeAuthorisation(RefTypeAuthorisation refTypeAuthorisation) {
        this.typeAuthorisation = refTypeAuthorisation;
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
        RefMatriceFlux refMatriceFlux = (RefMatriceFlux) o;
        if (refMatriceFlux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refMatriceFlux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefMatriceFlux{" +
            "id=" + getId() +
            ", environnement='" + getEnvironnement() + "'" +
            ", typeFlux='" + getTypeFlux() + "'" +
            ", sourceVlan='" + getSourceVlan() + "'" +
            ", port='" + getPort() + "'" +
            ", destVlan='" + getDestVlan() + "'" +
            ", typeAuthorisation='" + getTypeAuthorisation() + "'" +
            "}";
    }
}
