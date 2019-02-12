import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefSite } from 'app/shared/model/ref-site.model';
import { RefSiteService } from './ref-site.service';

@Component({
    selector: 'jhi-ref-site-delete-dialog',
    templateUrl: './ref-site-delete-dialog.component.html'
})
export class RefSiteDeleteDialogComponent {
    refSite: IRefSite;

    constructor(protected refSiteService: RefSiteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refSiteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'refSiteListModification',
                content: 'Deleted an refSite'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-site-delete-popup',
    template: ''
})
export class RefSiteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ refSite }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RefSiteDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.refSite = refSite;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ref-site', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ref-site', { outlets: { popup: null } }]);
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
