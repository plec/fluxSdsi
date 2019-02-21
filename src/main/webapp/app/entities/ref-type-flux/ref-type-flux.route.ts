import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefTypeFlux } from 'app/shared/model/ref-type-flux.model';
import { RefTypeFluxService } from './ref-type-flux.service';
import { RefTypeFluxComponent } from './ref-type-flux.component';
import { RefTypeFluxDetailComponent } from './ref-type-flux-detail.component';
import { RefTypeFluxUpdateComponent } from './ref-type-flux-update.component';
import { RefTypeFluxDeletePopupComponent } from './ref-type-flux-delete-dialog.component';
import { IRefTypeFlux } from 'app/shared/model/ref-type-flux.model';

@Injectable({ providedIn: 'root' })
export class RefTypeFluxResolve implements Resolve<IRefTypeFlux> {
    constructor(private service: RefTypeFluxService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefTypeFlux> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefTypeFlux>) => response.ok),
                map((refTypeFlux: HttpResponse<RefTypeFlux>) => refTypeFlux.body)
            );
        }
        return of(new RefTypeFlux());
    }
}

export const refTypeFluxRoute: Routes = [
    {
        path: '',
        component: RefTypeFluxComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefTypeFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefTypeFluxDetailComponent,
        resolve: {
            refTypeFlux: RefTypeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefTypeFluxUpdateComponent,
        resolve: {
            refTypeFlux: RefTypeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefTypeFluxUpdateComponent,
        resolve: {
            refTypeFlux: RefTypeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFluxes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refTypeFluxPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefTypeFluxDeletePopupComponent,
        resolve: {
            refTypeFlux: RefTypeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFluxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
