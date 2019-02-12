import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';
import { RefEnvironnementService } from './ref-environnement.service';

@Component({
    selector: 'jhi-ref-environnement-update',
    templateUrl: './ref-environnement-update.component.html'
})
export class RefEnvironnementUpdateComponent implements OnInit {
    refEnvironnement: IRefEnvironnement;
    isSaving: boolean;

    constructor(protected refEnvironnementService: RefEnvironnementService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refEnvironnement }) => {
            this.refEnvironnement = refEnvironnement;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refEnvironnement.id !== undefined) {
            this.subscribeToSaveResponse(this.refEnvironnementService.update(this.refEnvironnement));
        } else {
            this.subscribeToSaveResponse(this.refEnvironnementService.create(this.refEnvironnement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefEnvironnement>>) {
        result.subscribe((res: HttpResponse<IRefEnvironnement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
