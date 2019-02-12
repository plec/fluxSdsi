import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefTypeFonctionComponent,
    RefTypeFonctionDetailComponent,
    RefTypeFonctionUpdateComponent,
    RefTypeFonctionDeletePopupComponent,
    RefTypeFonctionDeleteDialogComponent,
    refTypeFonctionRoute,
    refTypeFonctionPopupRoute
} from './';

const ENTITY_STATES = [...refTypeFonctionRoute, ...refTypeFonctionPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefTypeFonctionComponent,
        RefTypeFonctionDetailComponent,
        RefTypeFonctionUpdateComponent,
        RefTypeFonctionDeleteDialogComponent,
        RefTypeFonctionDeletePopupComponent
    ],
    entryComponents: [
        RefTypeFonctionComponent,
        RefTypeFonctionUpdateComponent,
        RefTypeFonctionDeleteDialogComponent,
        RefTypeFonctionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefTypeFonctionModule {}
