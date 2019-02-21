import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IRefVlan } from 'app/shared/model/ref-vlan.model';
import { RefVlanService } from './ref-vlan.service';
import { IRefZone } from 'app/shared/model/ref-zone.model';
import { RefZoneService } from 'app/entities/ref-zone';

@Component({
    selector: 'jhi-ref-vlan-update',
    templateUrl: './ref-vlan-update.component.html'
})
export class RefVlanUpdateComponent implements OnInit {
    refVlan: IRefVlan;
    isSaving: boolean;

    refzones: IRefZone[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected refVlanService: RefVlanService,
        protected refZoneService: RefZoneService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refVlan }) => {
            this.refVlan = refVlan;
        });
        this.refZoneService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefZone[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefZone[]>) => response.body)
            )
            .subscribe((res: IRefZone[]) => (this.refzones = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refVlan.id !== undefined) {
            this.subscribeToSaveResponse(this.refVlanService.update(this.refVlan));
        } else {
            this.subscribeToSaveResponse(this.refVlanService.create(this.refVlan));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefVlan>>) {
        result.subscribe((res: HttpResponse<IRefVlan>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRefZoneById(index: number, item: IRefZone) {
        return item.id;
    }
}
