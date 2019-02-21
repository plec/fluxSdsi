import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';
import { RefTypeAuthorisationService } from './ref-type-authorisation.service';

@Component({
    selector: 'jhi-ref-type-authorisation-update',
    templateUrl: './ref-type-authorisation-update.component.html'
})
export class RefTypeAuthorisationUpdateComponent implements OnInit {
    refTypeAuthorisation: IRefTypeAuthorisation;
    isSaving: boolean;

    constructor(protected refTypeAuthorisationService: RefTypeAuthorisationService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refTypeAuthorisation }) => {
            this.refTypeAuthorisation = refTypeAuthorisation;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refTypeAuthorisation.id !== undefined) {
            this.subscribeToSaveResponse(this.refTypeAuthorisationService.update(this.refTypeAuthorisation));
        } else {
            this.subscribeToSaveResponse(this.refTypeAuthorisationService.create(this.refTypeAuthorisation));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefTypeAuthorisation>>) {
        result.subscribe(
            (res: HttpResponse<IRefTypeAuthorisation>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
