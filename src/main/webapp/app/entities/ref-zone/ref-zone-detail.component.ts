import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefZone } from 'app/shared/model/ref-zone.model';

@Component({
    selector: 'jhi-ref-zone-detail',
    templateUrl: './ref-zone-detail.component.html'
})
export class RefZoneDetailComponent implements OnInit {
    refZone: IRefZone;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refZone }) => {
            this.refZone = refZone;
        });
    }

    previousState() {
        window.history.back();
    }
}
