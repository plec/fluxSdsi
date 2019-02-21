import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemandeFlux } from 'app/shared/model/demande-flux.model';
import { DemandeFluxService } from './demande-flux.service';

@Component({
    selector: 'jhi-demande-flux-delete-dialog',
    templateUrl: './demande-flux-delete-dialog.component.html'
})
export class DemandeFluxDeleteDialogComponent {
    demandeFlux: IDemandeFlux;

    constructor(
        protected demandeFluxService: DemandeFluxService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.demandeFluxService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'demandeFluxListModification',
                content: 'Deleted an demandeFlux'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-demande-flux-delete-popup',
    template: ''
})
export class DemandeFluxDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ demandeFlux }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DemandeFluxDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.demandeFlux = demandeFlux;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/demande-flux', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/demande-flux', { outlets: { popup: null } }]);
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
