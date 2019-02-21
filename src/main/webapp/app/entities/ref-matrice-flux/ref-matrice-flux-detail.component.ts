import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

@Component({
    selector: 'jhi-ref-matrice-flux-detail',
    templateUrl: './ref-matrice-flux-detail.component.html'
})
export class RefMatriceFluxDetailComponent implements OnInit {
    refMatriceFlux: IRefMatriceFlux;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refMatriceFlux }) => {
            this.refMatriceFlux = refMatriceFlux;
        });
    }

    previousState() {
        window.history.back();
    }
}
