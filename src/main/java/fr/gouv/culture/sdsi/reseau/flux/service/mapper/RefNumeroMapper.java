package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefNumeroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefNumero and its DTO RefNumeroDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefNumeroMapper extends EntityMapper<RefNumeroDTO, RefNumero> {



    default RefNumero fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefNumero refNumero = new RefNumero();
        refNumero.setId(id);
        return refNumero;
    }
}
