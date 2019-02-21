import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefVlan } from 'app/shared/model/ref-vlan.model';
import { RefVlanService } from './ref-vlan.service';

@Component({
    selector: 'jhi-ref-vlan-delete-dialog',
    templateUrl: './ref-vlan-delete-dialog.component.html'
})
export class RefVlanDeleteDialogComponent {
    refVlan: IRefVlan;

    constructor(protected refVlanService: RefVlanService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refVlanService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refVlanListModification',
                content: 'Deleted an refVlan'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-vlan-delete-popup',
    template: ''
})
export class RefVlanDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refVlan }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefVlanDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refVlan = refVlan;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-vlan', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-vlan', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
