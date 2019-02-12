import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefNumero } from 'app/shared/model/ref-numero.model';

@Component({
    selector: 'jhi-ref-numero-detail',
    templateUrl: './ref-numero-detail.component.html'
})
export class RefNumeroDetailComponent implements OnInit {
    refNumero: IRefNumero;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refNumero }) => {
            this.refNumero = refNumero;
        });
    }

    previousState() {
        window.history.back();
    }
}
