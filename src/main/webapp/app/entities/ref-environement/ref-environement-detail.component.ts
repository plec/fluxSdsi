import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefEnvironement } from 'app/shared/model/ref-environement.model';

@Component({
    selector: 'jhi-ref-environement-detail',
    templateUrl: './ref-environement-detail.component.html'
})
export class RefEnvironementDetailComponent implements OnInit {
    refEnvironement: IRefEnvironement;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refEnvironement }) => {
            this.refEnvironement = refEnvironement;
        });
    }

    previousState() {
        window.history.back();
    }
}
