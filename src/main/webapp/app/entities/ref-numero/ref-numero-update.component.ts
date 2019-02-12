import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefNumero } from 'app/shared/model/ref-numero.model';
import { RefNumeroService } from './ref-numero.service';

@Component({
    selector: 'jhi-ref-numero-update',
    templateUrl: './ref-numero-update.component.html'
})
export class RefNumeroUpdateComponent implements OnInit {
    refNumero: IRefNumero;
    isSaving: boolean;

    constructor(protected refNumeroService: RefNumeroService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refNumero }) => {
            this.refNumero = refNumero;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refNumero.id !== undefined) {
            this.subscribeToSaveResponse(this.refNumeroService.update(this.refNumero));
        } else {
            this.subscribeToSaveResponse(this.refNumeroService.create(this.refNumero));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefNumero>>) {
        result.subscribe((res: HttpResponse<IRefNumero>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
