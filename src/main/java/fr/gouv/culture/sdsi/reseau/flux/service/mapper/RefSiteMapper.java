package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefSiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefSite and its DTO RefSiteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefSiteMapper extends EntityMapper<RefSiteDTO, RefSite> {



    default RefSite fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefSite refSite = new RefSite();
        refSite.setId(id);
        return refSite;
    }
}
