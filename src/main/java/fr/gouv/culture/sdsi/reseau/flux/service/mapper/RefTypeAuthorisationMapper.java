package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefTypeAuthorisationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefTypeAuthorisation and its DTO RefTypeAuthorisationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefTypeAuthorisationMapper extends EntityMapper<RefTypeAuthorisationDTO, RefTypeAuthorisation> {


    @Mapping(target = "codes", ignore = true)
    RefTypeAuthorisation toEntity(RefTypeAuthorisationDTO refTypeAuthorisationDTO);

    default RefTypeAuthorisation fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefTypeAuthorisation refTypeAuthorisation = new RefTypeAuthorisation();
        refTypeAuthorisation.setId(id);
        return refTypeAuthorisation;
    }
}
