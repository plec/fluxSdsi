import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefVlan } from 'app/shared/model/ref-vlan.model';
import { RefVlanService } from './ref-vlan.service';
import { RefVlanComponent } from './ref-vlan.component';
import { RefVlanDetailComponent } from './ref-vlan-detail.component';
import { RefVlanUpdateComponent } from './ref-vlan-update.component';
import { RefVlanDeletePopupComponent } from './ref-vlan-delete-dialog.component';
import { IRefVlan } from 'app/shared/model/ref-vlan.model';

@Injectable({ providedIn: 'root' })
export class RefVlanResolve implements Resolve<IRefVlan> {
    constructor(private service: RefVlanService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefVlan> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefVlan>) => response.ok),
                map((refVlan: HttpResponse<RefVlan>) => refVlan.body)
            );
        }
        return of(new RefVlan());
    }
}

export const refVlanRoute: Routes = [
    {
        path: '',
        component: RefVlanComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefVlans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefVlanDetailComponent,
        resolve: {
            refVlan: RefVlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefVlans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefVlanUpdateComponent,
        resolve: {
            refVlan: RefVlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefVlans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefVlanUpdateComponent,
        resolve: {
            refVlan: RefVlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefVlans'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refVlanPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefVlanDeletePopupComponent,
        resolve: {
            refVlan: RefVlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefVlans'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
