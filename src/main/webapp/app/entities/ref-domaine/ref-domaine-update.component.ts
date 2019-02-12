import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefDomaine } from 'app/shared/model/ref-domaine.model';
import { RefDomaineService } from './ref-domaine.service';

@Component({
    selector: 'jhi-ref-domaine-update',
    templateUrl: './ref-domaine-update.component.html'
})
export class RefDomaineUpdateComponent implements OnInit {
    refDomaine: IRefDomaine;
    isSaving: boolean;

    constructor(protected refDomaineService: RefDomaineService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refDomaine }) => {
            this.refDomaine = refDomaine;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refDomaine.id !== undefined) {
            this.subscribeToSaveResponse(this.refDomaineService.update(this.refDomaine));
        } else {
            this.subscribeToSaveResponse(this.refDomaineService.create(this.refDomaine));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefDomaine>>) {
        result.subscribe((res: HttpResponse<IRefDomaine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
