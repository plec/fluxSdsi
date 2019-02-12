package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.FluxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Flux and its DTO FluxDTO.
 */
@Mapper(componentModel = "spring", uses = {RefEnvironnementMapper.class, RefFluxMapper.class, RefZoneMapper.class})
public interface FluxMapper extends EntityMapper<FluxDTO, Flux> {

    @Mapping(source = "refEnvironnement.id", target = "refEnvironnementId")
    @Mapping(source = "refFlux.id", target = "refFluxId")
    @Mapping(source = "refZone.id", target = "refZoneId")
    FluxDTO toDto(Flux flux);

    @Mapping(source = "refEnvironnementId", target = "refEnvironnement")
    @Mapping(source = "refFluxId", target = "refFlux")
    @Mapping(source = "refZoneId", target = "refZone")
    Flux toEntity(FluxDTO fluxDTO);

    default Flux fromId(Long id) {
        if (id == null) {
            return null;
        }
        Flux flux = new Flux();
        flux.setId(id);
        return flux;
    }
}
