import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefEnvironnementComponent,
    RefEnvironnementDetailComponent,
    RefEnvironnementUpdateComponent,
    RefEnvironnementDeletePopupComponent,
    RefEnvironnementDeleteDialogComponent,
    refEnvironnementRoute,
    refEnvironnementPopupRoute
} from './';

const ENTITY_STATES = [...refEnvironnementRoute, ...refEnvironnementPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefEnvironnementComponent,
        RefEnvironnementDetailComponent,
        RefEnvironnementUpdateComponent,
        RefEnvironnementDeleteDialogComponent,
        RefEnvironnementDeletePopupComponent
    ],
    entryComponents: [
        RefEnvironnementComponent,
        RefEnvironnementUpdateComponent,
        RefEnvironnementDeleteDialogComponent,
        RefEnvironnementDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefEnvironnementModule {}
