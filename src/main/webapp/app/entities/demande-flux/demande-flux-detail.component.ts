import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemandeFlux } from 'app/shared/model/demande-flux.model';

@Component({
    selector: 'jhi-demande-flux-detail',
    templateUrl: './demande-flux-detail.component.html'
})
export class DemandeFluxDetailComponent implements OnInit {
    demandeFlux: IDemandeFlux;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ demandeFlux }) => {
            this.demandeFlux = demandeFlux;
        });
    }

    previousState() {
        window.history.back();
    }
}
