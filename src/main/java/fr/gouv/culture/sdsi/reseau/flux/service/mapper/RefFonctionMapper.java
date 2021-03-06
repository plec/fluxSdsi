package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.RefFonctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RefFonction and its DTO RefFonctionDTO.
 */
@Mapper(componentModel = "spring", uses = {RefTypeFonctionMapper.class})
public interface RefFonctionMapper extends EntityMapper<RefFonctionDTO, RefFonction> {

    @Mapping(source = "codeTypeFonction.id", target = "codeTypeFonctionId")
    RefFonctionDTO toDto(RefFonction refFonction);

    @Mapping(source = "codeTypeFonctionId", target = "codeTypeFonction")
    RefFonction toEntity(RefFonctionDTO refFonctionDTO);

    default RefFonction fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefFonction refFonction = new RefFonction();
        refFonction.setId(id);
        return refFonction;
    }
}
