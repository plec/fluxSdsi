import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';
import { RefMatriceFluxService } from './ref-matrice-flux.service';
import { RefMatriceFluxComponent } from './ref-matrice-flux.component';
import { RefMatriceFluxDetailComponent } from './ref-matrice-flux-detail.component';
import { RefMatriceFluxUpdateComponent } from './ref-matrice-flux-update.component';
import { RefMatriceFluxDeletePopupComponent } from './ref-matrice-flux-delete-dialog.component';
import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

@Injectable({ providedIn: 'root' })
export class RefMatriceFluxResolve implements Resolve<IRefMatriceFlux> {
    constructor(private service: RefMatriceFluxService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefMatriceFlux> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefMatriceFlux>) => response.ok),
                map((refMatriceFlux: HttpResponse<RefMatriceFlux>) => refMatriceFlux.body)
            );
        }
        return of(new RefMatriceFlux());
    }
}

export const refMatriceFluxRoute: Routes = [
    {
        path: '',
        component: RefMatriceFluxComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefMatriceFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefMatriceFluxDetailComponent,
        resolve: {
            refMatriceFlux: RefMatriceFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefMatriceFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefMatriceFluxUpdateComponent,
        resolve: {
            refMatriceFlux: RefMatriceFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefMatriceFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefMatriceFluxUpdateComponent,
        resolve: {
            refMatriceFlux: RefMatriceFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefMatriceFluxes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refMatriceFluxPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefMatriceFluxDeletePopupComponent,
        resolve: {
            refMatriceFlux: RefMatriceFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefMatriceFluxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
