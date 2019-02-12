package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeFonctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefTypeFonction and its DTO RefTypeFonctionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefTypeFonctionMapper extends EntityMapper<RefTypeFonctionDTO, RefTypeFonction> {


    @Mapping(target = "codes", ignore = true)
    RefTypeFonction toEntity(RefTypeFonctionDTO refTypeFonctionDTO);

    default RefTypeFonction fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefTypeFonction refTypeFonction = new RefTypeFonction();
        refTypeFonction.setId(id);
        return refTypeFonction;
    }
}
