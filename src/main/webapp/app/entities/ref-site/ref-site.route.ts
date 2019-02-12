import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefSite } from 'app/shared/model/ref-site.model';
import { RefSiteService } from './ref-site.service';
import { RefSiteComponent } from './ref-site.component';
import { RefSiteDetailComponent } from './ref-site-detail.component';
import { RefSiteUpdateComponent } from './ref-site-update.component';
import { RefSiteDeletePopupComponent } from './ref-site-delete-dialog.component';
import { IRefSite } from 'app/shared/model/ref-site.model';

@Injectable({ providedIn: 'root' })
export class RefSiteResolve implements Resolve<IRefSite> {
    constructor(private service: RefSiteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefSite> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefSite>) => response.ok),
                map((refSite: HttpResponse<RefSite>) => refSite.body)
            );
        }
        return of(new RefSite());
    }
}

export const refSiteRoute: Routes = [
    {
        path: '',
        component: RefSiteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefSites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefSiteDetailComponent,
        resolve: {
            refSite: RefSiteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefSiteUpdateComponent,
        resolve: {
            refSite: RefSiteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefSiteUpdateComponent,
        resolve: {
            refSite: RefSiteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSites'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refSitePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefSiteDeletePopupComponent,
        resolve: {
            refSite: RefSiteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefSites'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
