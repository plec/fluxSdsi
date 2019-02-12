package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefEnvironnementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefEnvironnement and its DTO RefEnvironnementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefEnvironnementMapper extends EntityMapper<RefEnvironnementDTO, RefEnvironnement> {


    @Mapping(target = "codes", ignore = true)
    RefEnvironnement toEntity(RefEnvironnementDTO refEnvironnementDTO);

    default RefEnvironnement fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefEnvironnement refEnvironnement = new RefEnvironnement();
        refEnvironnement.setId(id);
        return refEnvironnement;
    }
}
