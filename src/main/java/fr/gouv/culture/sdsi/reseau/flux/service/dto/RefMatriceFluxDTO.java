package fr.gouv.culture.sdsi.reseau.flux.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RefMatriceFlux entity.
 */
public class RefMatriceFluxDTO implements Serializable {

    private Long id;

    @NotNull
    private String environnement;

    @NotNull
    private String typeFlux;

    @NotNull
    private String sourceVlan;

    @NotNull
    private String port;

    @NotNull
    private String destVlan;

    @NotNull
    private String typeAuthorisation;


    private Long destVlanId;

    private Long sourceVlanId;

    private Long typeAuthorisationId;

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

    public String getSourceVlan() {
        return sourceVlan;
    }

    public void setSourceVlan(String sourceVlan) {
        this.sourceVlan = sourceVlan;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDestVlan() {
        return destVlan;
    }

    public void setDestVlan(String destVlan) {
        this.destVlan = destVlan;
    }

    public String getTypeAuthorisation() {
        return typeAuthorisation;
    }

    public void setTypeAuthorisation(String typeAuthorisation) {
        this.typeAuthorisation = typeAuthorisation;
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

    public Long getTypeAuthorisationId() {
        return typeAuthorisationId;
    }

    public void setTypeAuthorisationId(Long refTypeAuthorisationId) {
        this.typeAuthorisationId = refTypeAuthorisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RefMatriceFluxDTO refMatriceFluxDTO = (RefMatriceFluxDTO) o;
        if (refMatriceFluxDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refMatriceFluxDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefMatriceFluxDTO{" +
            "id=" + getId() +
            ", environnement='" + getEnvironnement() + "'" +
            ", typeFlux='" + getTypeFlux() + "'" +
            ", sourceVlan='" + getSourceVlan() + "'" +
            ", port='" + getPort() + "'" +
            ", destVlan='" + getDestVlan() + "'" +
            ", typeAuthorisation='" + getTypeAuthorisation() + "'" +
            ", destVlan=" + getDestVlanId() +
            ", sourceVlan=" + getSourceVlanId() +
            ", typeAuthorisation=" + getTypeAuthorisationId() +
            "}";
    }
}
