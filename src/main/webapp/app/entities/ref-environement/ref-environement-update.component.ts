import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefEnvironement } from 'app/shared/model/ref-environement.model';
import { RefEnvironementService } from './ref-environement.service';

@Component({
    selector: 'jhi-ref-environement-update',
    templateUrl: './ref-environement-update.component.html'
})
export class RefEnvironementUpdateComponent implements OnInit {
    refEnvironement: IRefEnvironement;
    isSaving: boolean;

    constructor(protected refEnvironementService: RefEnvironementService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refEnvironement }) => {
            this.refEnvironement = refEnvironement;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refEnvironement.id !== undefined) {
            this.subscribeToSaveResponse(this.refEnvironementService.update(this.refEnvironement));
        } else {
            this.subscribeToSaveResponse(this.refEnvironementService.create(this.refEnvironement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefEnvironement>>) {
        result.subscribe((res: HttpResponse<IRefEnvironement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
