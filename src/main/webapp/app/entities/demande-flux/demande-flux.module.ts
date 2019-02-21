import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    DemandeFluxComponent,
    DemandeFluxDetailComponent,
    DemandeFluxUpdateComponent,
    DemandeFluxDeletePopupComponent,
    DemandeFluxDeleteDialogComponent,
    demandeFluxRoute,
    demandeFluxPopupRoute
} from './';

const ENTITY_STATES = [...demandeFluxRoute, ...demandeFluxPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DemandeFluxComponent,
        DemandeFluxDetailComponent,
        DemandeFluxUpdateComponent,
        DemandeFluxDeleteDialogComponent,
        DemandeFluxDeletePopupComponent
    ],
    entryComponents: [DemandeFluxComponent, DemandeFluxUpdateComponent, DemandeFluxDeleteDialogComponent, DemandeFluxDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiDemandeFluxModule {}
