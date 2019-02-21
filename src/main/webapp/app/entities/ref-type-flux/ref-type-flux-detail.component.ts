import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefTypeFlux } from 'app/shared/model/ref-type-flux.model';

@Component({
    selector: 'jhi-ref-type-flux-detail',
    templateUrl: './ref-type-flux-detail.component.html'
})
export class RefTypeFluxDetailComponent implements OnInit {
    refTypeFlux: IRefTypeFlux;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refTypeFlux }) => {
            this.refTypeFlux = refTypeFlux;
        });
    }

    previousState() {
        window.history.back();
    }
}
