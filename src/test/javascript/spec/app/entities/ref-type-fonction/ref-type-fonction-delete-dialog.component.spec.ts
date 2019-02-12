/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeFonctionDeleteDialogComponent } from 'app/entities/ref-type-fonction/ref-type-fonction-delete-dialog.component';
import { RefTypeFonctionService } from 'app/entities/ref-type-fonction/ref-type-fonction.service';

describe('Component Tests', () => {
    describe('RefTypeFonction Management Delete Component', () => {
        let comp: RefTypeFonctionDeleteDialogComponent;
        let fixture: ComponentFixture<RefTypeFonctionDeleteDialogComponent>;
        let service: RefTypeFonctionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeFonctionDeleteDialogComponent]
            })
                .overrideTemplate(RefTypeFonctionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefTypeFonctionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeFonctionService);
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
