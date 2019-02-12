package fr.gouv.culture.sdsi.reseau.flux.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RefFonction entity.
 */
public class RefFonctionDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String codeZone;

    @NotNull
    private String codeFonction;

    @NotNull
    private String libelle;


    private Long codeFonctionId;

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

    public String getCodeFonction() {
        return codeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getCodeFonctionId() {
        return codeFonctionId;
    }

    public void setCodeFonctionId(Long refTypeFonctionId) {
        this.codeFonctionId = refTypeFonctionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RefFonctionDTO refFonctionDTO = (RefFonctionDTO) o;
        if (refFonctionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refFonctionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefFonctionDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", codeZone='" + getCodeZone() + "'" +
            ", codeFonction='" + getCodeFonction() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", codeFonction=" + getCodeFonctionId() +
            "}";
    }
}
