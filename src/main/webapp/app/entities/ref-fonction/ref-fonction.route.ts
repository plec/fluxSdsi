import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RefFonction } from 'app/shared/model/ref-fonction.model';
import { RefFonctionService } from './ref-fonction.service';
import { RefFonctionComponent } from './ref-fonction.component';
import { RefFonctionDetailComponent } from './ref-fonction-detail.component';
import { RefFonctionUpdateComponent } from './ref-fonction-update.component';
import { RefFonctionDeletePopupComponent } from './ref-fonction-delete-dialog.component';
import { IRefFonction } from 'app/shared/model/ref-fonction.model';

@Injectable({ providedIn: 'root' })
export class RefFonctionResolve implements Resolve<IRefFonction> {
    constructor(private service: RefFonctionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRefFonction> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RefFonction>) => response.ok),
                map((refFonction: HttpResponse<RefFonction>) => refFonction.body)
            );
        }
        return of(new RefFonction());
    }
}

export const refFonctionRoute: Routes = [
    {
        path: '',
        component: RefFonctionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'RefFonctions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RefFonctionDetailComponent,
        resolve: {
            refFonction: RefFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFonctions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RefFonctionUpdateComponent,
        resolve: {
            refFonction: RefFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFonctions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RefFonctionUpdateComponent,
        resolve: {
            refFonction: RefFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFonctions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refFonctionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RefFonctionDeletePopupComponent,
        resolve: {
            refFonction: RefFonctionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RefFonctions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
