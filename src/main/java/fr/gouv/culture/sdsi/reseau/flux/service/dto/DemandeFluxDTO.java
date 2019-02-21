package fr.gouv.culture.sdsi.reseau.flux.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DemandeFlux entity.
 */
public class DemandeFluxDTO implements Serializable {

    private Long id;

    @NotNull
    private String environnement;

    @NotNull
    private String typeFlux;

    @NotNull
    private String sourceIP;

    private String sourcePort;

    @NotNull
    private String sourceVlan;

    @NotNull
    private String destIP;

    private String destPort;

    @NotNull
    private String destVlan;


    private Long environnementId;

    private Long destVlanId;

    private Long sourceVlanId;

    private Long typeFluxId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnvironnement() {
        return environnement;
    }

    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    public String getTypeFlux() {
        return typeFlux;
    }

    public void setTypeFlux(String typeFlux) {
        this.typeFlux = typeFlux;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getSourceVlan() {
        return sourceVlan;
    }

    public void setSourceVlan(String sourceVlan) {
        this.sourceVlan = sourceVlan;
    }

    public String getDestIP() {
        return destIP;
    }

    public void setDestIP(String destIP) {
        this.destIP = destIP;
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getDestVlan() {
        return destVlan;
    }

    public void setDestVlan(String destVlan) {
        this.destVlan = destVlan;
    }

    public Long getEnvironnementId() {
        return environnementId;
    }

    public void setEnvironnementId(Long refEnvironementId) {
        this.environnementId = refEnvironementId;
    }

    public Long getDestVlanId() {
        return destVlanId;
    }

    public void setDestVlanId(Long refVlanId) {
        this.destVlanId = refVlanId;
    }

    public Long getSourceVlanId() {
        return sourceVlanId;
    }

    public void setSourceVlanId(Long refVlanId) {
        this.sourceVlanId = refVlanId;
    }

    public Long getTypeFluxId() {
        return typeFluxId;
    }

    public void setTypeFluxId(Long refTypeFluxId) {
        this.typeFluxId = refTypeFluxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DemandeFluxDTO demandeFluxDTO = (DemandeFluxDTO) o;
        if (demandeFluxDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeFluxDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandeFluxDTO{" +
            "id=" + getId() +
            ", environnement='" + getEnvironnement() + "'" +
            ", typeFlux='" + getTypeFlux() + "'" +
            ", sourceIP='" + getSourceIP() + "'" +
            ", sourcePort='" + getSourcePort() + "'" +
            ", sourceVlan='" + getSourceVlan() + "'" +
            ", destIP='" + getDestIP() + "'" +
            ", destPort='" + getDestPort() + "'" +
            ", destVlan='" + getDestVlan() + "'" +
            ", environnement=" + getEnvironnementId() +
            ", destVlan=" + getDestVlanId() +
            ", sourceVlan=" + getSourceVlanId() +
            ", typeFlux=" + getTypeFluxId() +
            "}";
    }
}
