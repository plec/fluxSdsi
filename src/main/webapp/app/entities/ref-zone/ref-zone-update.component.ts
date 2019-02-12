import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IRefZone } from 'app/shared/model/ref-zone.model';
import { RefZoneService } from './ref-zone.service';
import { IRefFonction } from 'app/shared/model/ref-fonction.model';
import { RefFonctionService } from 'app/entities/ref-fonction';

@Component({
    selector: 'jhi-ref-zone-update',
    templateUrl: './ref-zone-update.component.html'
})
export class RefZoneUpdateComponent implements OnInit {
    refZone: IRefZone;
    isSaving: boolean;

    reffonctions: IRefFonction[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected refZoneService: RefZoneService,
        protected refFonctionService: RefFonctionService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refZone }) => {
            this.refZone = refZone;
        });
        this.refFonctionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRefFonction[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRefFonction[]>) => response.body)
            )
            .subscribe((res: IRefFonction[]) => (this.reffonctions = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.refZone.id !== undefined) {
            this.subscribeToSaveResponse(this.refZoneService.update(this.refZone));
        } else {
            this.subscribeToSaveResponse(this.refZoneService.create(this.refZone));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefZone>>) {
        result.subscribe((res: HttpResponse<IRefZone>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRefFonctionById(index: number, item: IRefFonction) {
        return item.id;
    }
}
