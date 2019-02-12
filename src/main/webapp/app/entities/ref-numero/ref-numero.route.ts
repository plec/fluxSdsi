import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefNumero } from 'app/shared/model/ref-numero.model';
import { RefNumeroService } from './ref-numero.service';
import { RefNumeroComponent } from './ref-numero.component';
import { RefNumeroDetailComponent } from './ref-numero-detail.component';
import { RefNumeroUpdateComponent } from './ref-numero-update.component';
import { RefNumeroDeletePopupComponent } from './ref-numero-delete-dialog.component';
import { IRefNumero } from 'app/shared/model/ref-numero.model';

@Injectable({ providedIn: 'root' })
export class RefNumeroResolve implements Resolve<IRefNumero> {
    constructor(private service: RefNumeroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefNumero> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefNumero>) => response.ok),
                map((refNumero: HttpResponse<RefNumero>) => refNumero.body)
            );
        }
        return of(new RefNumero());
    }
}

export const refNumeroRoute: Routes = [
    {
        path: '',
        component: RefNumeroComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefNumeros'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefNumeroDetailComponent,
        resolve: {
            refNumero: RefNumeroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefNumeros'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefNumeroUpdateComponent,
        resolve: {
            refNumero: RefNumeroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefNumeros'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefNumeroUpdateComponent,
        resolve: {
            refNumero: RefNumeroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefNumeros'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refNumeroPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefNumeroDeletePopupComponent,
        resolve: {
            refNumero: RefNumeroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefNumeros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
