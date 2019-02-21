import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IRefZone } from 'app/shared/model/ref-zone.model';
import { RefZoneService } from './ref-zone.service';

@Component({
    selector: 'jhi-ref-zone-update',
    templateUrl: './ref-zone-update.component.html'
})
export class RefZoneUpdateComponent implements OnInit {
    refZone: IRefZone;
    isSaving: boolean;

    constructor(protected refZoneService: RefZoneService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ refZone }) => {
            this.refZone = refZone;
        });
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
}
