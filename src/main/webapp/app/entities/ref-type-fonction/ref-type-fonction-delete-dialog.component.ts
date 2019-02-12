import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefTypeFonction } from 'app/shared/model/ref-type-fonction.model';
import { RefTypeFonctionService } from './ref-type-fonction.service';

@Component({
    selector: 'jhi-ref-type-fonction-delete-dialog',
    templateUrl: './ref-type-fonction-delete-dialog.component.html'
})
export class RefTypeFonctionDeleteDialogComponent {
    refTypeFonction: IRefTypeFonction;

    constructor(
        protected refTypeFonctionService: RefTypeFonctionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refTypeFonctionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refTypeFonctionListModification',
                content: 'Deleted an refTypeFonction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-type-fonction-delete-popup',
    template: ''
})
export class RefTypeFonctionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refTypeFonction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefTypeFonctionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refTypeFonction = refTypeFonction;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-type-fonction', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-type-fonction', { outlets: { popup: null } }]);
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
