package fr.gouv.culture.sdsi.reseau.flux.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Flux entity.
 */
public class FluxDTO implements Serializable {

    private Long id;

    @NotNull
    private String environnement;

    @NotNull
    private String type;

    @NotNull
    private String sourceIP;

    private String sourcePort;

    @NotNull
    private String sourceZone;

    @NotNull
    private String destIP;

    private String destPort;

    @NotNull
    private String destZone;


    private Long refEnvironnementId;

    private Long refFluxId;

    private Long refZoneId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getSourceZone() {
        return sourceZone;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
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

    public String getDestZone() {
        return destZone;
    }

    public void setDestZone(String destZone) {
        this.destZone = destZone;
    }

    public Long getRefEnvironnementId() {
        return refEnvironnementId;
    }

    public void setRefEnvironnementId(Long refEnvironnementId) {
        this.refEnvironnementId = refEnvironnementId;
    }

    public Long getRefFluxId() {
        return refFluxId;
    }

    public void setRefFluxId(Long refFluxId) {
        this.refFluxId = refFluxId;
    }

    public Long getRefZoneId() {
        return refZoneId;
    }

    public void setRefZoneId(Long refZoneId) {
        this.refZoneId = refZoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FluxDTO fluxDTO = (FluxDTO) o;
        if (fluxDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fluxDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FluxDTO{" +
            "id=" + getId() +
            ", environnement='" + getEnvironnement() + "'" +
            ", type='" + getType() + "'" +
            ", sourceIP='" + getSourceIP() + "'" +
            ", sourcePort='" + getSourcePort() + "'" +
            ", sourceZone='" + getSourceZone() + "'" +
            ", destIP='" + getDestIP() + "'" +
            ", destPort='" + getDestPort() + "'" +
            ", destZone='" + getDestZone() + "'" +
            ", refEnvironnement=" + getRefEnvironnementId() +
            ", refFlux=" + getRefFluxId() +
            ", refZone=" + getRefZoneId() +
            "}";
    }
}
