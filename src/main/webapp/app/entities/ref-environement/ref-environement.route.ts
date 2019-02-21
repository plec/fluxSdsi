import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefEnvironement } from 'app/shared/model/ref-environement.model';
import { RefEnvironementService } from './ref-environement.service';
import { RefEnvironementComponent } from './ref-environement.component';
import { RefEnvironementDetailComponent } from './ref-environement-detail.component';
import { RefEnvironementUpdateComponent } from './ref-environement-update.component';
import { RefEnvironementDeletePopupComponent } from './ref-environement-delete-dialog.component';
import { IRefEnvironement } from 'app/shared/model/ref-environement.model';

@Injectable({ providedIn: 'root' })
export class RefEnvironementResolve implements Resolve<IRefEnvironement> {
    constructor(private service: RefEnvironementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefEnvironement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefEnvironement>) => response.ok),
                map((refEnvironement: HttpResponse<RefEnvironement>) => refEnvironement.body)
            );
        }
        return of(new RefEnvironement());
    }
}

export const refEnvironementRoute: Routes = [
    {
        path: '',
        component: RefEnvironementComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefEnvironements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefEnvironementDetailComponent,
        resolve: {
            refEnvironement: RefEnvironementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefEnvironementUpdateComponent,
        resolve: {
            refEnvironement: RefEnvironementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefEnvironementUpdateComponent,
        resolve: {
            refEnvironement: RefEnvironementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironements'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refEnvironementPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefEnvironementDeletePopupComponent,
        resolve: {
            refEnvironement: RefEnvironementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefEnvironements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
