import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefMatriceFluxComponent,
    RefMatriceFluxDetailComponent,
    RefMatriceFluxUpdateComponent,
    RefMatriceFluxDeletePopupComponent,
    RefMatriceFluxDeleteDialogComponent,
    refMatriceFluxRoute,
    refMatriceFluxPopupRoute
} from './';

const ENTITY_STATES = [...refMatriceFluxRoute, ...refMatriceFluxPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefMatriceFluxComponent,
        RefMatriceFluxDetailComponent,
        RefMatriceFluxUpdateComponent,
        RefMatriceFluxDeleteDialogComponent,
        RefMatriceFluxDeletePopupComponent
    ],
    entryComponents: [
        RefMatriceFluxComponent,
        RefMatriceFluxUpdateComponent,
        RefMatriceFluxDeleteDialogComponent,
        RefMatriceFluxDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefMatriceFluxModule {}
