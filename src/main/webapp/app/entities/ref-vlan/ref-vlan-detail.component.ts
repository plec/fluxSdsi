import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefVlan } from 'app/shared/model/ref-vlan.model';

@Component({
    selector: 'jhi-ref-vlan-detail',
    templateUrl: './ref-vlan-detail.component.html'
})
export class RefVlanDetailComponent implements OnInit {
    refVlan: IRefVlan;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refVlan }) => {
            this.refVlan = refVlan;
        });
    }

    previousState() {
        window.history.back();
    }
}
