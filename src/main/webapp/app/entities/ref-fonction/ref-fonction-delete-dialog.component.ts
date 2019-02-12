import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefFonction } from 'app/shared/model/ref-fonction.model';
import { RefFonctionService } from './ref-fonction.service';

@Component({
    selector: 'jhi-ref-fonction-delete-dialog',
    templateUrl: './ref-fonction-delete-dialog.component.html'
})
export class RefFonctionDeleteDialogComponent {
    refFonction: IRefFonction;

    constructor(
        protected refFonctionService: RefFonctionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refFonctionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refFonctionListModification',
                content: 'Deleted an refFonction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-fonction-delete-popup',
    template: ''
})
export class RefFonctionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refFonction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefFonctionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refFonction = refFonction;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-fonction', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-fonction', { outlets: { popup: null } }]);
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
