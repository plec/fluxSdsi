import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IFlux } from 'app/shared/model/flux.model';
import { FluxService } from './flux.service';
import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';
import { RefEnvironnementService } from 'app/entities/ref-environnement';
import { IRefZone } from 'app/shared/model/ref-zone.model';
import { RefZoneService } from 'app/entities/ref-zone';
import { IRefFlux } from 'app/shared/model/ref-flux.model';
import { RefFluxService } from 'app/entities/ref-flux';

@Component({
    selector: 'jhi-flux-update',
    templateUrl: './flux-update.component.html'
})
export class FluxUpdateComponent implements OnInit {
    flux: IFlux;
    isSaving: boolean;

    refenvironnements: IRefEnvironnement[];

    refzones: IRefZone[];

    reffluxes: IRefFlux[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected fluxService: FluxService,
        protected refEnvironnementService: RefEnvironnementService,
        protected refZoneService: RefZoneService,
        protected refFluxService: RefFluxService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ flux }) => {
            this.flux = flux;
        });
        this.refEnvironnementService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefEnvironnement[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefEnvironnement[]>) => response.body)
            )
            .subscribe((res: IRefEnvironnement[]) => (this.refenvironnements = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.refZoneService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefZone[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefZone[]>) => response.body)
            )
            .subscribe((res: IRefZone[]) => (this.refzones = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.refFluxService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefFlux[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefFlux[]>) => response.body)
            )
            .subscribe((res: IRefFlux[]) => (this.reffluxes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.flux.id !== undefined) {
            this.subscribeToSaveResponse(this.fluxService.update(this.flux));
        } else {
            this.subscribeToSaveResponse(this.fluxService.create(this.flux));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFlux>>) {
        result.subscribe((res: HttpResponse<IFlux>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRefEnvironnementById(index: number, item: IRefEnvironnement) {
        return item.id;
    }

    trackRefZoneById(index: number, item: IRefZone) {
        return item.id;
    }

    trackRefFluxById(index: number, item: IRefFlux) {
        return item.id;
    }
}
