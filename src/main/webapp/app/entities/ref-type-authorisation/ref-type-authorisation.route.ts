import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';
import { RefTypeAuthorisationService } from './ref-type-authorisation.service';
import { RefTypeAuthorisationComponent } from './ref-type-authorisation.component';
import { RefTypeAuthorisationDetailComponent } from './ref-type-authorisation-detail.component';
import { RefTypeAuthorisationUpdateComponent } from './ref-type-authorisation-update.component';
import { RefTypeAuthorisationDeletePopupComponent } from './ref-type-authorisation-delete-dialog.component';
import { IRefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';

@Injectable({ providedIn: 'root' })
export class RefTypeAuthorisationResolve implements Resolve<IRefTypeAuthorisation> {
    constructor(private service: RefTypeAuthorisationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefTypeAuthorisation> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefTypeAuthorisation>) => response.ok),
                map((refTypeAuthorisation: HttpResponse<RefTypeAuthorisation>) => refTypeAuthorisation.body)
            );
        }
        return of(new RefTypeAuthorisation());
    }
}

export const refTypeAuthorisationRoute: Routes = [
    {
        path: '',
        component: RefTypeAuthorisationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefTypeAuthorisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefTypeAuthorisationDetailComponent,
        resolve: {
            refTypeAuthorisation: RefTypeAuthorisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeAuthorisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefTypeAuthorisationUpdateComponent,
        resolve: {
            refTypeAuthorisation: RefTypeAuthorisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeAuthorisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefTypeAuthorisationUpdateComponent,
        resolve: {
            refTypeAuthorisation: RefTypeAuthorisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeAuthorisations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refTypeAuthorisationPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefTypeAuthorisationDeletePopupComponent,
        resolve: {
            refTypeAuthorisation: RefTypeAuthorisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeAuthorisations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
