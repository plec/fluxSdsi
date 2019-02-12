import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefSite } from 'app/shared/model/ref-site.model';
import { RefSiteService } from './ref-site.service';

@Component({
    selector: 'jhi-ref-site-update',
    templateUrl: './ref-site-update.component.html'
})
export class RefSiteUpdateComponent implements OnInit {
    refSite: IRefSite;
    isSaving: boolean;

    constructor(protected refSiteService: RefSiteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refSite }) => {
            this.refSite = refSite;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refSite.id !== undefined) {
            this.subscribeToSaveResponse(this.refSiteService.update(this.refSite));
        } else {
            this.subscribeToSaveResponse(this.refSiteService.create(this.refSite));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefSite>>) {
        result.subscribe((res: HttpResponse<IRefSite>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
