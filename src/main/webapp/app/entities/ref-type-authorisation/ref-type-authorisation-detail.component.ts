import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';

@Component({
    selector: 'jhi-ref-type-authorisation-detail',
    templateUrl: './ref-type-authorisation-detail.component.html'
})
export class RefTypeAuthorisationDetailComponent implements OnInit {
    refTypeAuthorisation: IRefTypeAuthorisation;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refTypeAuthorisation }) => {
            this.refTypeAuthorisation = refTypeAuthorisation;
        });
    }

    previousState() {
        window.history.back();
    }
}
