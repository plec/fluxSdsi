package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.FluxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Flux and its DTO FluxDTO.
 */
@Mapper(componentModel = "spring", uses = {RefEnvironnementMapper.class, RefZoneMapper.class, RefFluxMapper.class})
public interface FluxMapper extends EntityMapper<FluxDTO, Flux> {

    @Mapping(source = "environnement.id", target = "environnementId")
    @Mapping(source = "sourceZone.id", target = "sourceZoneId")
    @Mapping(source = "destZone.id", target = "destZoneId")
    @Mapping(source = "type.id", target = "typeId")
    FluxDTO toDto(Flux flux);

    @Mapping(source = "environnementId", target = "environnement")
    @Mapping(source = "sourceZoneId", target = "sourceZone")
    @Mapping(source = "destZoneId", target = "destZone")
    @Mapping(source = "typeId", target = "type")
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
