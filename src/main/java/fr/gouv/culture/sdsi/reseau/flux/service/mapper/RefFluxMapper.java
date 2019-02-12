package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFluxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefFlux and its DTO RefFluxDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefFluxMapper extends EntityMapper<RefFluxDTO, RefFlux> {


    @Mapping(target = "codes", ignore = true)
    RefFlux toEntity(RefFluxDTO refFluxDTO);

    default RefFlux fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefFlux refFlux = new RefFlux();
        refFlux.setId(id);
        return refFlux;
    }
}
