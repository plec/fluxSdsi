package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefDomaineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefDomaine and its DTO RefDomaineDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefDomaineMapper extends EntityMapper<RefDomaineDTO, RefDomaine> {



    default RefDomaine fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefDomaine refDomaine = new RefDomaine();
        refDomaine.setId(id);
        return refDomaine;
    }
}
