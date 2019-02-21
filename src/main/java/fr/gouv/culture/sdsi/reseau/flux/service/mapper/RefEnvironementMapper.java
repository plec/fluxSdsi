package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefEnvironement and its DTO RefEnvironementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefEnvironementMapper extends EntityMapper<RefEnvironementDTO, RefEnvironement> {


    @Mapping(target = "codes", ignore = true)
    RefEnvironement toEntity(RefEnvironementDTO refEnvironementDTO);

    default RefEnvironement fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefEnvironement refEnvironement = new RefEnvironement();
        refEnvironement.setId(id);
        return refEnvironement;
    }
}
