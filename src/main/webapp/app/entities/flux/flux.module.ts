import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    FluxComponent,
    FluxDetailComponent,
    FluxUpdateComponent,
    FluxDeletePopupComponent,
    FluxDeleteDialogComponent,
    fluxRoute,
    fluxPopupRoute
} from './';

const ENTITY_STATES = [...fluxRoute, ...fluxPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [FluxComponent, FluxDetailComponent, FluxUpdateComponent, FluxDeleteDialogComponent, FluxDeletePopupComponent],
    entryComponents: [FluxComponent, FluxUpdateComponent, FluxDeleteDialogComponent, FluxDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiFluxModule {}
