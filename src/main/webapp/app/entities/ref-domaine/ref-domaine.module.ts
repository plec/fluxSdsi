import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefDomaineComponent,
    RefDomaineDetailComponent,
    RefDomaineUpdateComponent,
    RefDomaineDeletePopupComponent,
    RefDomaineDeleteDialogComponent,
    refDomaineRoute,
    refDomainePopupRoute
} from './';

const ENTITY_STATES = [...refDomaineRoute, ...refDomainePopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefDomaineComponent,
        RefDomaineDetailComponent,
        RefDomaineUpdateComponent,
        RefDomaineDeleteDialogComponent,
        RefDomaineDeletePopupComponent
    ],
    entryComponents: [RefDomaineComponent, RefDomaineUpdateComponent, RefDomaineDeleteDialogComponent, RefDomaineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefDomaineModule {}
