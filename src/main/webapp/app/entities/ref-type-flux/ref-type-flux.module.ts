import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefTypeFluxComponent,
    RefTypeFluxDetailComponent,
    RefTypeFluxUpdateComponent,
    RefTypeFluxDeletePopupComponent,
    RefTypeFluxDeleteDialogComponent,
    refTypeFluxRoute,
    refTypeFluxPopupRoute
} from './';

const ENTITY_STATES = [...refTypeFluxRoute, ...refTypeFluxPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefTypeFluxComponent,
        RefTypeFluxDetailComponent,
        RefTypeFluxUpdateComponent,
        RefTypeFluxDeleteDialogComponent,
        RefTypeFluxDeletePopupComponent
    ],
    entryComponents: [RefTypeFluxComponent, RefTypeFluxUpdateComponent, RefTypeFluxDeleteDialogComponent, RefTypeFluxDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefTypeFluxModule {}
