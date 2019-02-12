import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IRefFonction } from 'app/shared/model/ref-fonction.model';
import { RefFonctionService } from './ref-fonction.service';
import { IRefTypeFonction } from 'app/shared/model/ref-type-fonction.model';
import { RefTypeFonctionService } from 'app/entities/ref-type-fonction';

@Component({
    selector: 'jhi-ref-fonction-update',
    templateUrl: './ref-fonction-update.component.html'
})
export class RefFonctionUpdateComponent implements OnInit {
    refFonction: IRefFonction;
    isSaving: boolean;

    reftypefonctions: IRefTypeFonction[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected refFonctionService: RefFonctionService,
        protected refTypeFonctionService: RefTypeFonctionService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refFonction }) => {
            this.refFonction = refFonction;
        });
        this.refTypeFonctionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefTypeFonction[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefTypeFonction[]>) => response.body)
            )
            .subscribe((res: IRefTypeFonction[]) => (this.reftypefonctions = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refFonction.id !== undefined) {
            this.subscribeToSaveResponse(this.refFonctionService.update(this.refFonction));
        } else {
            this.subscribeToSaveResponse(this.refFonctionService.create(this.refFonction));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefFonction>>) {
        result.subscribe((res: HttpResponse<IRefFonction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRefTypeFonctionById(index: number, item: IRefTypeFonction) {
        return item.id;
    }
}
