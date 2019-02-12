import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefEnvironnement } from 'app/shared/model/ref-environnement.model';
import { RefEnvironnementService } from './ref-environnement.service';
import { RefEnvironnementComponent } from './ref-environnement.component';
import { RefEnvironnementDetailComponent } from './ref-environnement-detail.component';
import { RefEnvironnementUpdateComponent } from './ref-environnement-update.component';
import { RefEnvironnementDeletePopupComponent } from './ref-environnement-delete-dialog.component';
import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';

@Injectable({ providedIn: 'root' })
export class RefEnvironnementResolve implements Resolve<IRefEnvironnement> {
    constructor(private service: RefEnvironnementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefEnvironnement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefEnvironnement>) => response.ok),
                map((refEnvironnement: HttpResponse<RefEnvironnement>) => refEnvironnement.body)
            );
        }
        return of(new RefEnvironnement());
    }
}

export const refEnvironnementRoute: Routes = [
    {
        path: '',
        component: RefEnvironnementComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefEnvironnements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefEnvironnementDetailComponent,
        resolve: {
            refEnvironnement: RefEnvironnementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironnements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefEnvironnementUpdateComponent,
        resolve: {
            refEnvironnement: RefEnvironnementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironnements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefEnvironnementUpdateComponent,
        resolve: {
            refEnvironnement: RefEnvironnementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironnements'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refEnvironnementPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefEnvironnementDeletePopupComponent,
        resolve: {
            refEnvironnement: RefEnvironnementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironnements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
