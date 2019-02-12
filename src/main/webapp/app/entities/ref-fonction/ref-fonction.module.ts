import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefFonctionComponent,
    RefFonctionDetailComponent,
    RefFonctionUpdateComponent,
    RefFonctionDeletePopupComponent,
    RefFonctionDeleteDialogComponent,
    refFonctionRoute,
    refFonctionPopupRoute
} from './';

const ENTITY_STATES = [...refFonctionRoute, ...refFonctionPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefFonctionComponent,
        RefFonctionDetailComponent,
        RefFonctionUpdateComponent,
        RefFonctionDeleteDialogComponent,
        RefFonctionDeletePopupComponent
    ],
    entryComponents: [RefFonctionComponent, RefFonctionUpdateComponent, RefFonctionDeleteDialogComponent, RefFonctionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefFonctionModule {}
