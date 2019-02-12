import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefFlux } from 'app/shared/model/ref-flux.model';
import { RefFluxService } from './ref-flux.service';

@Component({
    selector: 'jhi-ref-flux-update',
    templateUrl: './ref-flux-update.component.html'
})
export class RefFluxUpdateComponent implements OnInit {
    refFlux: IRefFlux;
    isSaving: boolean;

    constructor(protected refFluxService: RefFluxService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refFlux }) => {
            this.refFlux = refFlux;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refFlux.id !== undefined) {
            this.subscribeToSaveResponse(this.refFluxService.update(this.refFlux));
        } else {
            this.subscribeToSaveResponse(this.refFluxService.create(this.refFlux));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefFlux>>) {
        result.subscribe((res: HttpResponse<IRefFlux>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
