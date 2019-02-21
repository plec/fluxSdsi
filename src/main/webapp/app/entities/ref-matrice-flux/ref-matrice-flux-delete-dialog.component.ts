import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';
import { RefMatriceFluxService } from './ref-matrice-flux.service';

@Component({
    selector: 'jhi-ref-matrice-flux-delete-dialog',
    templateUrl: './ref-matrice-flux-delete-dialog.component.html'
})
export class RefMatriceFluxDeleteDialogComponent {
    refMatriceFlux: IRefMatriceFlux;

    constructor(
        protected refMatriceFluxService: RefMatriceFluxService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refMatriceFluxService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refMatriceFluxListModification',
                content: 'Deleted an refMatriceFlux'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-matrice-flux-delete-popup',
    template: ''
})
export class RefMatriceFluxDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refMatriceFlux }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefMatriceFluxDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refMatriceFlux = refMatriceFlux;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-matrice-flux', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-matrice-flux', { outlets: { popup: null } }]);
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
