package fr.gouv.culture.sdsi.reseau.flux.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import fr.gouv.culture.sdsi.reseau.flux.domain.enumeration.TypeFlux;

/**
 * A DTO for the RefFlux entity.
 */
public class RefFluxDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private TypeFlux type;

    @NotNull
    private String libelle;


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

    public TypeFlux getType() {
        return type;
    }

    public void setType(TypeFlux type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RefFluxDTO refFluxDTO = (RefFluxDTO) o;
        if (refFluxDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refFluxDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefFluxDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", type='" + getType() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
