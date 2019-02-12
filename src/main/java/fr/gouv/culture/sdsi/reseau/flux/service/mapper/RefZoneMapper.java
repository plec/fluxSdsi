package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefZoneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefZone and its DTO RefZoneDTO.
 */
@Mapper(componentModel = "spring", uses = {RefFonctionMapper.class})
public interface RefZoneMapper extends EntityMapper<RefZoneDTO, RefZone> {

    @Mapping(source = "code.id", target = "codeId")
    RefZoneDTO toDto(RefZone refZone);

    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(source = "codeId", target = "code")
    RefZone toEntity(RefZoneDTO refZoneDTO);

    default RefZone fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefZone refZone = new RefZone();
        refZone.setId(id);
        return refZone;
    }
}
