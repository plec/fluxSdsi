import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefDomaine } from 'app/shared/model/ref-domaine.model';
import { RefDomaineService } from './ref-domaine.service';
import { RefDomaineComponent } from './ref-domaine.component';
import { RefDomaineDetailComponent } from './ref-domaine-detail.component';
import { RefDomaineUpdateComponent } from './ref-domaine-update.component';
import { RefDomaineDeletePopupComponent } from './ref-domaine-delete-dialog.component';
import { IRefDomaine } from 'app/shared/model/ref-domaine.model';

@Injectable({ providedIn: 'root' })
export class RefDomaineResolve implements Resolve<IRefDomaine> {
    constructor(private service: RefDomaineService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefDomaine> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefDomaine>) => response.ok),
                map((refDomaine: HttpResponse<RefDomaine>) => refDomaine.body)
            );
        }
        return of(new RefDomaine());
    }
}

export const refDomaineRoute: Routes = [
    {
        path: '',
        component: RefDomaineComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefDomaines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefDomaineDetailComponent,
        resolve: {
            refDomaine: RefDomaineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDomaines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefDomaineUpdateComponent,
        resolve: {
            refDomaine: RefDomaineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDomaines'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefDomaineUpdateComponent,
        resolve: {
            refDomaine: RefDomaineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDomaines'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refDomainePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefDomaineDeletePopupComponent,
        resolve: {
            refDomaine: RefDomaineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefDomaines'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
