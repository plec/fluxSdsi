import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefFlux } from 'app/shared/model/ref-flux.model';
import { RefFluxService } from './ref-flux.service';

@Component({
    selector: 'jhi-ref-flux-delete-dialog',
    templateUrl: './ref-flux-delete-dialog.component.html'
})
export class RefFluxDeleteDialogComponent {
    refFlux: IRefFlux;

    constructor(protected refFluxService: RefFluxService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refFluxService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refFluxListModification',
                content: 'Deleted an refFlux'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-flux-delete-popup',
    template: ''
})
export class RefFluxDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refFlux }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefFluxDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refFlux = refFlux;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-flux', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-flux', { outlets: { popup: null } }]);
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
