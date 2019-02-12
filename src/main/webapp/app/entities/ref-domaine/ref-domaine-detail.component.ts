import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefDomaine } from 'app/shared/model/ref-domaine.model';

@Component({
    selector: 'jhi-ref-domaine-detail',
    templateUrl: './ref-domaine-detail.component.html'
})
export class RefDomaineDetailComponent implements OnInit {
    refDomaine: IRefDomaine;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refDomaine }) => {
            this.refDomaine = refDomaine;
        });
    }

    previousState() {
        window.history.back();
    }
}
