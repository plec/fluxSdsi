import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';
import { RefMatriceFluxService } from './ref-matrice-flux.service';
import { IRefVlan } from 'app/shared/model/ref-vlan.model';
import { RefVlanService } from 'app/entities/ref-vlan';
import { IRefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';
import { RefTypeAuthorisationService } from 'app/entities/ref-type-authorisation';

@Component({
    selector: 'jhi-ref-matrice-flux-update',
    templateUrl: './ref-matrice-flux-update.component.html'
})
export class RefMatriceFluxUpdateComponent implements OnInit {
    refMatriceFlux: IRefMatriceFlux;
    isSaving: boolean;

    refvlans: IRefVlan[];

    reftypeauthorisations: IRefTypeAuthorisation[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected refMatriceFluxService: RefMatriceFluxService,
        protected refVlanService: RefVlanService,
        protected refTypeAuthorisationService: RefTypeAuthorisationService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refMatriceFlux }) => {
            this.refMatriceFlux = refMatriceFlux;
        });
        this.refVlanService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefVlan[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefVlan[]>) => response.body)
            )
            .subscribe((res: IRefVlan[]) => (this.refvlans = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.refTypeAuthorisationService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefTypeAuthorisation[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefTypeAuthorisation[]>) => response.body)
            )
            .subscribe(
                (res: IRefTypeAuthorisation[]) => (this.reftypeauthorisations = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refMatriceFlux.id !== undefined) {
            this.subscribeToSaveResponse(this.refMatriceFluxService.update(this.refMatriceFlux));
        } else {
            this.subscribeToSaveResponse(this.refMatriceFluxService.create(this.refMatriceFlux));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefMatriceFlux>>) {
        result.subscribe((res: HttpResponse<IRefMatriceFlux>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRefVlanById(index: number, item: IRefVlan) {
        return item.id;
    }

    trackRefTypeAuthorisationById(index: number, item: IRefTypeAuthorisation) {
        return item.id;
    }
}
