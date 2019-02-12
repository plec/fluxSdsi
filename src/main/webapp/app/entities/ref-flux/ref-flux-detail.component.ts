import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefFlux } from 'app/shared/model/ref-flux.model';

@Component({
    selector: 'jhi-ref-flux-detail',
    templateUrl: './ref-flux-detail.component.html'
})
export class RefFluxDetailComponent implements OnInit {
    refFlux: IRefFlux;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refFlux }) => {
            this.refFlux = refFlux;
        });
    }

    previousState() {
        window.history.back();
    }
}
