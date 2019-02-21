/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeAuthorisationDeleteDialogComponent } from 'app/entities/ref-type-authorisation/ref-type-authorisation-delete-dialog.component';
import { RefTypeAuthorisationService } from 'app/entities/ref-type-authorisation/ref-type-authorisation.service';

describe('Component Tests', () => {
    describe('RefTypeAuthorisation Management Delete Component', () => {
        let comp: RefTypeAuthorisationDeleteDialogComponent;
        let fixture: ComponentFixture<RefTypeAuthorisationDeleteDialogComponent>;
        let service: RefTypeAuthorisationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeAuthorisationDeleteDialogComponent]
            })
                .overrideTemplate(RefTypeAuthorisationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefTypeAuthorisationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeAuthorisationService);
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
