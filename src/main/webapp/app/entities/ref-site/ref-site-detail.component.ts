import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefSite } from 'app/shared/model/ref-site.model';

@Component({
    selector: 'jhi-ref-site-detail',
    templateUrl: './ref-site-detail.component.html'
})
export class RefSiteDetailComponent implements OnInit {
    refSite: IRefSite;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refSite }) => {
            this.refSite = refSite;
        });
    }

    previousState() {
        window.history.back();
    }
}
