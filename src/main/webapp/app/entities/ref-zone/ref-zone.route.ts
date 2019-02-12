import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefZone } from 'app/shared/model/ref-zone.model';
import { RefZoneService } from './ref-zone.service';
import { RefZoneComponent } from './ref-zone.component';
import { RefZoneDetailComponent } from './ref-zone-detail.component';
import { RefZoneUpdateComponent } from './ref-zone-update.component';
import { RefZoneDeletePopupComponent } from './ref-zone-delete-dialog.component';
import { IRefZone } from 'app/shared/model/ref-zone.model';

@Injectable({ providedIn: 'root' })
export class RefZoneResolve implements Resolve<IRefZone> {
    constructor(private service: RefZoneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefZone> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefZone>) => response.ok),
                map((refZone: HttpResponse<RefZone>) => refZone.body)
            );
        }
        return of(new RefZone());
    }
}

export const refZoneRoute: Routes = [
    {
        path: '',
        component: RefZoneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefZones'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefZoneDetailComponent,
        resolve: {
            refZone: RefZoneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefZones'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefZoneUpdateComponent,
        resolve: {
            refZone: RefZoneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefZones'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefZoneUpdateComponent,
        resolve: {
            refZone: RefZoneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefZones'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refZonePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefZoneDeletePopupComponent,
        resolve: {
            refZone: RefZoneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefZones'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
