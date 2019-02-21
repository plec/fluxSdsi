import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDemandeFlux } from 'app/shared/model/demande-flux.model';
import { DemandeFluxService } from './demande-flux.service';
import { IRefEnvironement } from 'app/shared/model/ref-environement.model';
import { RefEnvironementService } from 'app/entities/ref-environement';
import { IRefVlan } from 'app/shared/model/ref-vlan.model';
import { RefVlanService } from 'app/entities/ref-vlan';
import { IRefTypeFlux } from 'app/shared/model/ref-type-flux.model';
import { RefTypeFluxService } from 'app/entities/ref-type-flux';

@Component({
    selector: 'jhi-demande-flux-update',
    templateUrl: './demande-flux-update.component.html'
})
export class DemandeFluxUpdateComponent implements OnInit {
    demandeFlux: IDemandeFlux;
    isSaving: boolean;

    refenvironements: IRefEnvironement[];

    refvlans: IRefVlan[];

    reftypefluxes: IRefTypeFlux[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected demandeFluxService: DemandeFluxService,
        protected refEnvironementService: RefEnvironementService,
        protected refVlanService: RefVlanService,
        protected refTypeFluxService: RefTypeFluxService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ demandeFlux }) => {
            this.demandeFlux = demandeFlux;
        });
        this.refEnvironementService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefEnvironement[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefEnvironement[]>) => response.body)
            )
            .subscribe((res: IRefEnvironement[]) => (this.refenvironements = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.refVlanService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefVlan[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefVlan[]>) => response.body)
            )
            .subscribe((res: IRefVlan[]) => (this.refvlans = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.refTypeFluxService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefTypeFlux[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefTypeFlux[]>) => response.body)
            )
            .subscribe((res: IRefTypeFlux[]) => (this.reftypefluxes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.demandeFlux.id !== undefined) {
            this.subscribeToSaveResponse(this.demandeFluxService.update(this.demandeFlux));
        } else {
            this.subscribeToSaveResponse(this.demandeFluxService.create(this.demandeFlux));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeFlux>>) {
        result.subscribe((res: HttpResponse<IDemandeFlux>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRefEnvironementById(index: number, item: IRefEnvironement) {
        return item.id;
    }

    trackRefVlanById(index: number, item: IRefVlan) {
        return item.id;
    }

    trackRefTypeFluxById(index: number, item: IRefTypeFlux) {
        return item.id;
    }
}
