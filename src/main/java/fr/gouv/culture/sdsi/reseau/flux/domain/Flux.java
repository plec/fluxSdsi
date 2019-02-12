package fr.gouv.culture.sdsi.reseau.flux.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Flux.
 */
@Entity
@Table(name = "flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Flux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "environnement", nullable = false)
    private String environnement;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "source_ip", nullable = false)
    private String sourceIP;

    @Column(name = "source_port")
    private String sourcePort;

    @NotNull
    @Column(name = "source_zone", nullable = false)
    private String sourceZone;

    @NotNull
    @Column(name = "dest_ip", nullable = false)
    private String destIP;

    @Column(name = "dest_port")
    private String destPort;

    @NotNull
    @Column(name = "dest_zone", nullable = false)
    private String destZone;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefEnvironnement refEnvironnement;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefFlux refFlux;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefZone refZone;

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

    public Flux environnement(String environnement) {
        this.environnement = environnement;
        return this;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public String getType() {
        return type;
    }

    public Flux type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public Flux sourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
        return this;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public Flux sourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
        return this;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getSourceZone() {
        return sourceZone;
    }

    public Flux sourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
        return this;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
    }

    public String getDestIP() {
        return destIP;
    }

    public Flux destIP(String destIP) {
        this.destIP = destIP;
        return this;
    }

    public void setDestIP(String destIP) {
        this.destIP = destIP;
    }

    public String getDestPort() {
        return destPort;
    }

    public Flux destPort(String destPort) {
        this.destPort = destPort;
        return this;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getDestZone() {
        return destZone;
    }

    public Flux destZone(String destZone) {
        this.destZone = destZone;
        return this;
    }

    public void setDestZone(String destZone) {
        this.destZone = destZone;
    }

    public RefEnvironnement getRefEnvironnement() {
        return refEnvironnement;
    }

    public Flux refEnvironnement(RefEnvironnement refEnvironnement) {
        this.refEnvironnement = refEnvironnement;
        return this;
    }

    public void setRefEnvironnement(RefEnvironnement refEnvironnement) {
        this.refEnvironnement = refEnvironnement;
    }

    public RefFlux getRefFlux() {
        return refFlux;
    }

    public Flux refFlux(RefFlux refFlux) {
        this.refFlux = refFlux;
        return this;
    }

    public void setRefFlux(RefFlux refFlux) {
        this.refFlux = refFlux;
    }

    public RefZone getRefZone() {
        return refZone;
    }

    public Flux refZone(RefZone refZone) {
        this.refZone = refZone;
        return this;
    }

    public void setRefZone(RefZone refZone) {
        this.refZone = refZone;
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
        Flux flux = (Flux) o;
        if (flux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), flux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Flux{" +
            "id=" + getId() +
            ", environnement='" + getEnvironnement() + "'" +
            ", type='" + getType() + "'" +
            ", sourceIP='" + getSourceIP() + "'" +
            ", sourcePort='" + getSourcePort() + "'" +
            ", sourceZone='" + getSourceZone() + "'" +
            ", destIP='" + getDestIP() + "'" +
            ", destPort='" + getDestPort() + "'" +
            ", destZone='" + getDestZone() + "'" +
            "}";
    }
}
