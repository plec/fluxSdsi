import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefVlanComponent,
    RefVlanDetailComponent,
    RefVlanUpdateComponent,
    RefVlanDeletePopupComponent,
    RefVlanDeleteDialogComponent,
    refVlanRoute,
    refVlanPopupRoute
} from './';

const ENTITY_STATES = [...refVlanRoute, ...refVlanPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefVlanComponent,
        RefVlanDetailComponent,
        RefVlanUpdateComponent,
        RefVlanDeleteDialogComponent,
        RefVlanDeletePopupComponent
    ],
    entryComponents: [RefVlanComponent, RefVlanUpdateComponent, RefVlanDeleteDialogComponent, RefVlanDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefVlanModule {}
