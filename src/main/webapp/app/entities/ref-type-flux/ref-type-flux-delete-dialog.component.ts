import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefTypeFlux } from 'app/shared/model/ref-type-flux.model';
import { RefTypeFluxService } from './ref-type-flux.service';

@Component({
    selector: 'jhi-ref-type-flux-delete-dialog',
    templateUrl: './ref-type-flux-delete-dialog.component.html'
})
export class RefTypeFluxDeleteDialogComponent {
    refTypeFlux: IRefTypeFlux;

    constructor(
        protected refTypeFluxService: RefTypeFluxService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refTypeFluxService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refTypeFluxListModification',
                content: 'Deleted an refTypeFlux'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-type-flux-delete-popup',
    template: ''
})
export class RefTypeFluxDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refTypeFlux }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefTypeFluxDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refTypeFlux = refTypeFlux;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-type-flux', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-type-flux', { outlets: { popup: null } }]);
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
