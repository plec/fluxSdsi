import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefFlux } from 'app/shared/model/ref-flux.model';
import { RefFluxService } from './ref-flux.service';
import { RefFluxComponent } from './ref-flux.component';
import { RefFluxDetailComponent } from './ref-flux-detail.component';
import { RefFluxUpdateComponent } from './ref-flux-update.component';
import { RefFluxDeletePopupComponent } from './ref-flux-delete-dialog.component';
import { IRefFlux } from 'app/shared/model/ref-flux.model';

@Injectable({ providedIn: 'root' })
export class RefFluxResolve implements Resolve<IRefFlux> {
    constructor(private service: RefFluxService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefFlux> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefFlux>) => response.ok),
                map((refFlux: HttpResponse<RefFlux>) => refFlux.body)
            );
        }
        return of(new RefFlux());
    }
}

export const refFluxRoute: Routes = [
    {
        path: '',
        component: RefFluxComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefFluxDetailComponent,
        resolve: {
            refFlux: RefFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefFluxUpdateComponent,
        resolve: {
            refFlux: RefFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefFluxUpdateComponent,
        resolve: {
            refFlux: RefFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFluxes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refFluxPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefFluxDeletePopupComponent,
        resolve: {
            refFlux: RefFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFluxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
