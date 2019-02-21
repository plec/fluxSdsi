import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefEnvironement } from 'app/shared/model/ref-environement.model';
import { RefEnvironementService } from './ref-environement.service';

@Component({
    selector: 'jhi-ref-environement-delete-dialog',
    templateUrl: './ref-environement-delete-dialog.component.html'
})
export class RefEnvironementDeleteDialogComponent {
    refEnvironement: IRefEnvironement;

    constructor(
        protected refEnvironementService: RefEnvironementService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refEnvironementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refEnvironementListModification',
                content: 'Deleted an refEnvironement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-environement-delete-popup',
    template: ''
})
export class RefEnvironementDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refEnvironement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefEnvironementDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refEnvironement = refEnvironement;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-environement', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-environement', { outlets: { popup: null } }]);
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
