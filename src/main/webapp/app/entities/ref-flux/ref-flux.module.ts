import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefFluxComponent,
    RefFluxDetailComponent,
    RefFluxUpdateComponent,
    RefFluxDeletePopupComponent,
    RefFluxDeleteDialogComponent,
    refFluxRoute,
    refFluxPopupRoute
} from './';

const ENTITY_STATES = [...refFluxRoute, ...refFluxPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefFluxComponent,
        RefFluxDetailComponent,
        RefFluxUpdateComponent,
        RefFluxDeleteDialogComponent,
        RefFluxDeletePopupComponent
    ],
    entryComponents: [RefFluxComponent, RefFluxUpdateComponent, RefFluxDeleteDialogComponent, RefFluxDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefFluxModule {}
