import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefNumero } from 'app/shared/model/ref-numero.model';
import { RefNumeroService } from './ref-numero.service';

@Component({
    selector: 'jhi-ref-numero-delete-dialog',
    templateUrl: './ref-numero-delete-dialog.component.html'
})
export class RefNumeroDeleteDialogComponent {
    refNumero: IRefNumero;

    constructor(
        protected refNumeroService: RefNumeroService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refNumeroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refNumeroListModification',
                content: 'Deleted an refNumero'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-numero-delete-popup',
    template: ''
})
export class RefNumeroDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refNumero }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefNumeroDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refNumero = refNumero;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-numero', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-numero', { outlets: { popup: null } }]);
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
