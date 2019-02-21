package fr.gouv.culture.sdsi.reseau.flux.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RefVlan entity.
 */
public class RefVlanDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String codeZone;

    @NotNull
    private String libelle;


    private Long codeZoneId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeZone() {
        return codeZone;
    }

    public void setCodeZone(String codeZone) {
        this.codeZone = codeZone;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getCodeZoneId() {
        return codeZoneId;
    }

    public void setCodeZoneId(Long refZoneId) {
        this.codeZoneId = refZoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RefVlanDTO refVlanDTO = (RefVlanDTO) o;
        if (refVlanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refVlanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefVlanDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", codeZone='" + getCodeZone() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", codeZone=" + getCodeZoneId() +
            "}";
    }
}
