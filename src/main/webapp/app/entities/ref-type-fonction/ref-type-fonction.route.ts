import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefTypeFonction } from 'app/shared/model/ref-type-fonction.model';
import { RefTypeFonctionService } from './ref-type-fonction.service';
import { RefTypeFonctionComponent } from './ref-type-fonction.component';
import { RefTypeFonctionDetailComponent } from './ref-type-fonction-detail.component';
import { RefTypeFonctionUpdateComponent } from './ref-type-fonction-update.component';
import { RefTypeFonctionDeletePopupComponent } from './ref-type-fonction-delete-dialog.component';
import { IRefTypeFonction } from 'app/shared/model/ref-type-fonction.model';

@Injectable({ providedIn: 'root' })
export class RefTypeFonctionResolve implements Resolve<IRefTypeFonction> {
    constructor(private service: RefTypeFonctionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefTypeFonction> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefTypeFonction>) => response.ok),
                map((refTypeFonction: HttpResponse<RefTypeFonction>) => refTypeFonction.body)
            );
        }
        return of(new RefTypeFonction());
    }
}

export const refTypeFonctionRoute: Routes = [
    {
        path: '',
        component: RefTypeFonctionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefTypeFonctions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefTypeFonctionDetailComponent,
        resolve: {
            refTypeFonction: RefTypeFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFonctions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefTypeFonctionUpdateComponent,
        resolve: {
            refTypeFonction: RefTypeFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFonctions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefTypeFonctionUpdateComponent,
        resolve: {
            refTypeFonction: RefTypeFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFonctions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refTypeFonctionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefTypeFonctionDeletePopupComponent,
        resolve: {
            refTypeFonction: RefTypeFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefTypeFonctions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
