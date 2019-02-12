import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefTypeFonction } from 'app/shared/model/ref-type-fonction.model';
import { RefTypeFonctionService } from './ref-type-fonction.service';

@Component({
    selector: 'jhi-ref-type-fonction-update',
    templateUrl: './ref-type-fonction-update.component.html'
})
export class RefTypeFonctionUpdateComponent implements OnInit {
    refTypeFonction: IRefTypeFonction;
    isSaving: boolean;

    constructor(protected refTypeFonctionService: RefTypeFonctionService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refTypeFonction }) => {
            this.refTypeFonction = refTypeFonction;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refTypeFonction.id !== undefined) {
            this.subscribeToSaveResponse(this.refTypeFonctionService.update(this.refTypeFonction));
        } else {
            this.subscribeToSaveResponse(this.refTypeFonctionService.create(this.refTypeFonction));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefTypeFonction>>) {
        result.subscribe((res: HttpResponse<IRefTypeFonction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
