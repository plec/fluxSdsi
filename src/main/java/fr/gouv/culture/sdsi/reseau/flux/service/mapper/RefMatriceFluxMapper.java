package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefMatriceFluxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefMatriceFlux and its DTO RefMatriceFluxDTO.
 */
@Mapper(componentModel = "spring", uses = {RefVlanMapper.class, RefTypeAuthorisationMapper.class})
public interface RefMatriceFluxMapper extends EntityMapper<RefMatriceFluxDTO, RefMatriceFlux> {

    @Mapping(source = "destVlan.id", target = "destVlanId")
    @Mapping(source = "sourceVlan.id", target = "sourceVlanId")
    @Mapping(source = "typeAuthorisation.id", target = "typeAuthorisationId")
    RefMatriceFluxDTO toDto(RefMatriceFlux refMatriceFlux);

    @Mapping(source = "destVlanId", target = "destVlan")
    @Mapping(source = "sourceVlanId", target = "sourceVlan")
    @Mapping(source = "typeAuthorisationId", target = "typeAuthorisation")
    RefMatriceFlux toEntity(RefMatriceFluxDTO refMatriceFluxDTO);

    default RefMatriceFlux fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefMatriceFlux refMatriceFlux = new RefMatriceFlux();
        refMatriceFlux.setId(id);
        return refMatriceFlux;
    }
}
