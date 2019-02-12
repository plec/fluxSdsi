import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefFonction } from 'app/shared/model/ref-fonction.model';

@Component({
    selector: 'jhi-ref-fonction-detail',
    templateUrl: './ref-fonction-detail.component.html'
})
export class RefFonctionDetailComponent implements OnInit {
    refFonction: IRefFonction;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refFonction }) => {
            this.refFonction = refFonction;
        });
    }

    previousState() {
        window.history.back();
    }
}
