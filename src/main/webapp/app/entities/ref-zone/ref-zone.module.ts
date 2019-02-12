import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefZoneComponent,
    RefZoneDetailComponent,
    RefZoneUpdateComponent,
    RefZoneDeletePopupComponent,
    RefZoneDeleteDialogComponent,
    refZoneRoute,
    refZonePopupRoute
} from './';

const ENTITY_STATES = [...refZoneRoute, ...refZonePopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefZoneComponent,
        RefZoneDetailComponent,
        RefZoneUpdateComponent,
        RefZoneDeleteDialogComponent,
        RefZoneDeletePopupComponent
    ],
    entryComponents: [RefZoneComponent, RefZoneUpdateComponent, RefZoneDeleteDialogComponent, RefZoneDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefZoneModule {}
