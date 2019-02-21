import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefTypeAuthorisationComponent,
    RefTypeAuthorisationDetailComponent,
    RefTypeAuthorisationUpdateComponent,
    RefTypeAuthorisationDeletePopupComponent,
    RefTypeAuthorisationDeleteDialogComponent,
    refTypeAuthorisationRoute,
    refTypeAuthorisationPopupRoute
} from './';

const ENTITY_STATES = [...refTypeAuthorisationRoute, ...refTypeAuthorisationPopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefTypeAuthorisationComponent,
        RefTypeAuthorisationDetailComponent,
        RefTypeAuthorisationUpdateComponent,
        RefTypeAuthorisationDeleteDialogComponent,
        RefTypeAuthorisationDeletePopupComponent
    ],
    entryComponents: [
        RefTypeAuthorisationComponent,
        RefTypeAuthorisationUpdateComponent,
        RefTypeAuthorisationDeleteDialogComponent,
        RefTypeAuthorisationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefTypeAuthorisationModule {}
