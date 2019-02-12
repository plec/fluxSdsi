import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefZone } from 'app/shared/model/ref-zone.model';
import { RefZoneService } from './ref-zone.service';

@Component({
    selector: 'jhi-ref-zone-delete-dialog',
    templateUrl: './ref-zone-delete-dialog.component.html'
})
export class RefZoneDeleteDialogComponent {
    refZone: IRefZone;

    constructor(protected refZoneService: RefZoneService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refZoneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refZoneListModification',
                content: 'Deleted an refZone'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-zone-delete-popup',
    template: ''
})
export class RefZoneDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refZone }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefZoneDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refZone = refZone;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-zone', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-zone', { outlets: { popup: null } }]);
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
