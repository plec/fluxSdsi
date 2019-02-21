package fr.gouv.culture.sdsi.reseau.flux.service.mapper;

import fr.gouv.culture.sdsi.reseau.flux.domain.*;
import fr.gouv.culture.sdsi.reseau.flux.service.dto.DemandeFluxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DemandeFlux and its DTO DemandeFluxDTO.
 */
@Mapper(componentModel = "spring", uses = {RefEnvironementMapper.class, RefVlanMapper.class, RefTypeFluxMapper.class})
public interface DemandeFluxMapper extends EntityMapper<DemandeFluxDTO, DemandeFlux> {

    @Mapping(source = "environnement.id", target = "environnementId")
    @Mapping(source = "destVlan.id", target = "destVlanId")
    @Mapping(source = "sourceVlan.id", target = "sourceVlanId")
    @Mapping(source = "typeFlux.id", target = "typeFluxId")
    DemandeFluxDTO toDto(DemandeFlux demandeFlux);

    @Mapping(source = "environnementId", target = "environnement")
    @Mapping(source = "destVlanId", target = "destVlan")
    @Mapping(source = "sourceVlanId", target = "sourceVlan")
    @Mapping(source = "typeFluxId", target = "typeFlux")
    DemandeFlux toEntity(DemandeFluxDTO demandeFluxDTO);

    default DemandeFlux fromId(Long id) {
        if (id == null) {
            return null;
        }
        DemandeFlux demandeFlux = new DemandeFlux();
        demandeFlux.setId(id);
        return demandeFlux;
    }
}
