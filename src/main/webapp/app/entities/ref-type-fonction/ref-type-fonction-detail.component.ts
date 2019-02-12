import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefTypeFonction } from 'app/shared/model/ref-type-fonction.model';

@Component({
    selector: 'jhi-ref-type-fonction-detail',
    templateUrl: './ref-type-fonction-detail.component.html'
})
export class RefTypeFonctionDetailComponent implements OnInit {
    refTypeFonction: IRefTypeFonction;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refTypeFonction }) => {
            this.refTypeFonction = refTypeFonction;
        });
    }

    previousState() {
        window.history.back();
    }
}
