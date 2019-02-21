package fr.gouv.culture.sdsi.reseau.flux.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DemandeFlux.
 */
@Entity
@Table(name = "demande_flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemandeFlux implements Serializable {

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
    @Column(name = "source_ip", nullable = false)
    private String sourceIP;

    @Column(name = "source_port")
    private String sourcePort;

    @NotNull
    @Column(name = "source_vlan", nullable = false)
    private String sourceVlan;

    @NotNull
    @Column(name = "dest_ip", nullable = false)
    private String destIP;

    @Column(name = "dest_port")
    private String destPort;

    @NotNull
    @Column(name = "dest_vlan", nullable = false)
    private String destVlan;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefEnvironement environnement;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefVlan destVlan;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefVlan sourceVlan;

    @ManyToOne
    @JsonIgnoreProperties("codes")
    private RefTypeFlux typeFlux;

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

    public DemandeFlux environnement(String environnement) {
        this.environnement = environnement;
        return this;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public String getTypeFlux() {
        return typeFlux;
    }

    public DemandeFlux typeFlux(String typeFlux) {
        this.typeFlux = typeFlux;
        return this;
    }

    public void setTypeFlux(String typeFlux) {
        this.typeFlux = typeFlux;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public DemandeFlux sourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
        return this;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public DemandeFlux sourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
        return this;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getSourceVlan() {
        return sourceVlan;
    }

    public DemandeFlux sourceVlan(String sourceVlan) {
        this.sourceVlan = sourceVlan;
        return this;
    }

    public void setSourceVlan(String sourceVlan) {
        this.sourceVlan = sourceVlan;
    }

    public String getDestIP() {
        return destIP;
    }

    public DemandeFlux destIP(String destIP) {
        this.destIP = destIP;
        return this;
    }

    public void setDestIP(String destIP) {
        this.destIP = destIP;
    }

    public String getDestPort() {
        return destPort;
    }

    public DemandeFlux destPort(String destPort) {
        this.destPort = destPort;
        return this;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getDestVlan() {
        return destVlan;
    }

    public DemandeFlux destVlan(String destVlan) {
        this.destVlan = destVlan;
        return this;
    }

    public void setDestVlan(String destVlan) {
        this.destVlan = destVlan;
    }

    public RefEnvironement getEnvironnement() {
        return environnement;
    }

    public DemandeFlux environnement(RefEnvironement refEnvironement) {
        this.environnement = refEnvironement;
        return this;
    }

    public void setEnvironnement(RefEnvironement refEnvironement) {
        this.environnement = refEnvironement;
    }

    public RefVlan getDestVlan() {
        return destVlan;
    }

    public DemandeFlux destVlan(RefVlan refVlan) {
        this.destVlan = refVlan;
        return this;
    }

    public void setDestVlan(RefVlan refVlan) {
        this.destVlan = refVlan;
    }

    public RefVlan getSourceVlan() {
        return sourceVlan;
    }

    public DemandeFlux sourceVlan(RefVlan refVlan) {
        this.sourceVlan = refVlan;
        return this;
    }

    public void setSourceVlan(RefVlan refVlan) {
        this.sourceVlan = refVlan;
    }

    public RefTypeFlux getTypeFlux() {
        return typeFlux;
    }

    public DemandeFlux typeFlux(RefTypeFlux refTypeFlux) {
        this.typeFlux = refTypeFlux;
        return this;
    }

    public void setTypeFlux(RefTypeFlux refTypeFlux) {
        this.typeFlux = refTypeFlux;
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
        DemandeFlux demandeFlux = (DemandeFlux) o;
        if (demandeFlux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeFlux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandeFlux{" +
            "id=" + getId() +
            ", environnement='" + getEnvironnement() + "'" +
            ", typeFlux='" + getTypeFlux() + "'" +
            ", sourceIP='" + getSourceIP() + "'" +
            ", sourcePort='" + getSourcePort() + "'" +
            ", sourceVlan='" + getSourceVlan() + "'" +
            ", destIP='" + getDestIP() + "'" +
            ", destPort='" + getDestPort() + "'" +
            ", destVlan='" + getDestVlan() + "'" +
            "}";
    }
}
