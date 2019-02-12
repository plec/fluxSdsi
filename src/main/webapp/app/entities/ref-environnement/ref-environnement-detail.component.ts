import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';

@Component({
    selector: 'jhi-ref-environnement-detail',
    templateUrl: './ref-environnement-detail.component.html'
})
export class RefEnvironnementDetailComponent implements OnInit {
    refEnvironnement: IRefEnvironnement;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refEnvironnement }) => {
            this.refEnvironnement = refEnvironnement;
        });
    }

    previousState() {
        window.history.back();
    }
}
