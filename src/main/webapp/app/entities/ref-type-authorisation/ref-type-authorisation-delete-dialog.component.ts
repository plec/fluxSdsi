import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';
import { RefTypeAuthorisationService } from './ref-type-authorisation.service';

@Component({
    selector: 'jhi-ref-type-authorisation-delete-dialog',
    templateUrl: './ref-type-authorisation-delete-dialog.component.html'
})
export class RefTypeAuthorisationDeleteDialogComponent {
    refTypeAuthorisation: IRefTypeAuthorisation;

    constructor(
        protected refTypeAuthorisationService: RefTypeAuthorisationService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refTypeAuthorisationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refTypeAuthorisationListModification',
                content: 'Deleted an refTypeAuthorisation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-type-authorisation-delete-popup',
    template: ''
})
export class RefTypeAuthorisationDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refTypeAuthorisation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefTypeAuthorisationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.refTypeAuthorisation = refTypeAuthorisation;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-type-authorisation', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-type-authorisation', { outlets: { popup: null } }]);
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
