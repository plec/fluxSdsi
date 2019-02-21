import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DemandeFlux } from 'app/shared/model/demande-flux.model';
import { DemandeFluxService } from './demande-flux.service';
import { DemandeFluxComponent } from './demande-flux.component';
import { DemandeFluxDetailComponent } from './demande-flux-detail.component';
import { DemandeFluxUpdateComponent } from './demande-flux-update.component';
import { DemandeFluxDeletePopupComponent } from './demande-flux-delete-dialog.component';
import { IDemandeFlux } from 'app/shared/model/demande-flux.model';

@Injectable({ providedIn: 'root' })
export class DemandeFluxResolve implements Resolve<IDemandeFlux> {
    constructor(private service: DemandeFluxService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDemandeFlux> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DemandeFlux>) => response.ok),
                map((demandeFlux: HttpResponse<DemandeFlux>) => demandeFlux.body)
            );
        }
        return of(new DemandeFlux());
    }
}

export const demandeFluxRoute: Routes = [
    {
        path: '',
        component: DemandeFluxComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'DemandeFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DemandeFluxDetailComponent,
        resolve: {
            demandeFlux: DemandeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DemandeFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DemandeFluxUpdateComponent,
        resolve: {
            demandeFlux: DemandeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DemandeFluxes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DemandeFluxUpdateComponent,
        resolve: {
            demandeFlux: DemandeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DemandeFluxes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const demandeFluxPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DemandeFluxDeletePopupComponent,
        resolve: {
            demandeFlux: DemandeFluxResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DemandeFluxes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
