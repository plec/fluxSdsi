import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefDomaine } from 'app/shared/model/ref-domaine.model';
import { RefDomaineService } from './ref-domaine.service';

@Component({
    selector: 'jhi-ref-domaine-delete-dialog',
    templateUrl: './ref-domaine-delete-dialog.component.html'
})
export class RefDomaineDeleteDialogComponent {
    refDomaine: IRefDomaine;

    constructor(
        protected refDomaineService: RefDomaineService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refDomaineService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refDomaineListModification',
                content: 'Deleted an refDomaine'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-domaine-delete-popup',
    template: ''
})
export class RefDomaineDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refDomaine }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefDomaineDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refDomaine = refDomaine;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-domaine', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-domaine', { outlets: { popup: null } }]);
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
