/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeFluxDeleteDialogComponent } from 'app/entities/ref-type-flux/ref-type-flux-delete-dialog.component';
import { RefTypeFluxService } from 'app/entities/ref-type-flux/ref-type-flux.service';

describe('Component Tests', () => {
    describe('RefTypeFlux Management Delete Component', () => {
        let comp: RefTypeFluxDeleteDialogComponent;
        let fixture: ComponentFixture<RefTypeFluxDeleteDialogComponent>;
        let service: RefTypeFluxService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeFluxDeleteDialogComponent]
            })
                .overrideTemplate(RefTypeFluxDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefTypeFluxDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeFluxService);
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
