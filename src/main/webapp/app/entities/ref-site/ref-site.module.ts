import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FluxSdsiSharedModule } from 'app/shared';
import {
    RefSiteComponent,
    RefSiteDetailComponent,
    RefSiteUpdateComponent,
    RefSiteDeletePopupComponent,
    RefSiteDeleteDialogComponent,
    refSiteRoute,
    refSitePopupRoute
} from './';

const ENTITY_STATES = [...refSiteRoute, ...refSitePopupRoute];

@NgModule({
    imports: [FluxSdsiSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RefSiteComponent,
        RefSiteDetailComponent,
        RefSiteUpdateComponent,
        RefSiteDeleteDialogComponent,
        RefSiteDeletePopupComponent
    ],
    entryComponents: [RefSiteComponent, RefSiteUpdateComponent, RefSiteDeleteDialogComponent, RefSiteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FluxSdsiRefSiteModule {}
