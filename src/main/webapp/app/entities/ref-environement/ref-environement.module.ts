import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefEnvironementComponent,
    RefEnvironementDetailComponent,
    RefEnvironementUpdateComponent,
    RefEnvironementDeletePopupComponent,
    RefEnvironementDeleteDialogComponent,
    refEnvironementRoute,
    refEnvironementPopupRoute
} from './';

const ENTITY_STATES = [...refEnvironementRoute, ...refEnvironementPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefEnvironementComponent,
        RefEnvironementDetailComponent,
        RefEnvironementUpdateComponent,
        RefEnvironementDeleteDialogComponent,
        RefEnvironementDeletePopupComponent
    ],
    entryComponents: [
        RefEnvironementComponent,
        RefEnvironementUpdateComponent,
        RefEnvironementDeleteDialogComponent,
        RefEnvironementDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefEnvironementModule {}
