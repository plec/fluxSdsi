package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFluxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefTypeFlux and its DTO RefTypeFluxDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefTypeFluxMapper extends EntityMapper<RefTypeFluxDTO, RefTypeFlux> {


    @Mapping(target = "codes", ignore = true)
    RefTypeFlux toEntity(RefTypeFluxDTO refTypeFluxDTO);

    default RefTypeFlux fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefTypeFlux refTypeFlux = new RefTypeFlux();
        refTypeFlux.setId(id);
        return refTypeFlux;
    }
}
