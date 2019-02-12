import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefNumeroComponent,
    RefNumeroDetailComponent,
    RefNumeroUpdateComponent,
    RefNumeroDeletePopupComponent,
    RefNumeroDeleteDialogComponent,
    refNumeroRoute,
    refNumeroPopupRoute
} from './';

const ENTITY_STATES = [...refNumeroRoute, ...refNumeroPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefNumeroComponent,
        RefNumeroDetailComponent,
        RefNumeroUpdateComponent,
        RefNumeroDeleteDialogComponent,
        RefNumeroDeletePopupComponent
    ],
    entryComponents: [RefNumeroComponent, RefNumeroUpdateComponent, RefNumeroDeleteDialogComponent, RefNumeroDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefNumeroModule {}
