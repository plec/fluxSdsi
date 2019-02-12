import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';
import { RefEnvironnementService } from './ref-environnement.service';

@Component({
    selector: 'jhi-ref-environnement-delete-dialog',
    templateUrl: './ref-environnement-delete-dialog.component.html'
})
export class RefEnvironnementDeleteDialogComponent {
    refEnvironnement: IRefEnvironnement;

    constructor(
        protected refEnvironnementService: RefEnvironnementService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refEnvironnementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refEnvironnementListModification',
                content: 'Deleted an refEnvironnement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-environnement-delete-popup',
    template: ''
})
export class RefEnvironnementDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refEnvironnement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefEnvironnementDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refEnvironnement = refEnvironnement;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-environnement', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-environnement', { outlets: { popup: null } }]);
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
