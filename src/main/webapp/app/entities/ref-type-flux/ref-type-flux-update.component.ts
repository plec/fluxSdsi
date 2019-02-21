import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefTypeFlux } from 'app/shared/model/ref-type-flux.model';
import { RefTypeFluxService } from './ref-type-flux.service';

@Component({
    selector: 'jhi-ref-type-flux-update',
    templateUrl: './ref-type-flux-update.component.html'
})
export class RefTypeFluxUpdateComponent implements OnInit {
    refTypeFlux: IRefTypeFlux;
    isSaving: boolean;

    constructor(protected refTypeFluxService: RefTypeFluxService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refTypeFlux }) => {
            this.refTypeFlux = refTypeFlux;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refTypeFlux.id !== undefined) {
            this.subscribeToSaveResponse(this.refTypeFluxService.update(this.refTypeFlux));
        } else {
            this.subscribeToSaveResponse(this.refTypeFluxService.create(this.refTypeFlux));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefTypeFlux>>) {
        result.subscribe((res: HttpResponse<IRefTypeFlux>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
