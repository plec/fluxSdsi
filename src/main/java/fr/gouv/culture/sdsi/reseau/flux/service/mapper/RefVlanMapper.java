package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefVlanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefVlan and its DTO RefVlanDTO.
 */
@Mapper(componentModel = "spring", uses = {RefZoneMapper.class})
public interface RefVlanMapper extends EntityMapper<RefVlanDTO, RefVlan> {

    @Mapping(source = "codeZone.id", target = "codeZoneId")
    RefVlanDTO toDto(RefVlan refVlan);

    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(target = "codes", ignore = true)
    @Mapping(source = "codeZoneId", target = "codeZone")
    RefVlan toEntity(RefVlanDTO refVlanDTO);

    default RefVlan fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefVlan refVlan = new RefVlan();
        refVlan.setId(id);
        return refVlan;
    }
}
