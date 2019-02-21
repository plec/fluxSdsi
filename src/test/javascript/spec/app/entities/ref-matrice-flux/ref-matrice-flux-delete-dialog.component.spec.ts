/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefMatriceFluxDeleteDialogComponent } from 'app/entities/ref-matrice-flux/ref-matrice-flux-delete-dialog.component';
import { RefMatriceFluxService } from 'app/entities/ref-matrice-flux/ref-matrice-flux.service';

describe('Component Tests', () => {
    describe('RefMatriceFlux Management Delete Component', () => {
        let comp: RefMatriceFluxDeleteDialogComponent;
        let fixture: ComponentFixture<RefMatriceFluxDeleteDialogComponent>;
        let service: RefMatriceFluxService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefMatriceFluxDeleteDialogComponent]
            })
                .overrideTemplate(RefMatriceFluxDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefMatriceFluxDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefMatriceFluxService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
