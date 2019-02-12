/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefFonctionDeleteDialogComponent } from 'app/entities/ref-fonction/ref-fonction-delete-dialog.component';
import { RefFonctionService } from 'app/entities/ref-fonction/ref-fonction.service';

describe('Component Tests', () => {
    describe('RefFonction Management Delete Component', () => {
        let comp: RefFonctionDeleteDialogComponent;
        let fixture: ComponentFixture<RefFonctionDeleteDialogComponent>;
        let service: RefFonctionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefFonctionDeleteDialogComponent]
            })
                .overrideTemplate(RefFonctionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefFonctionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefFonctionService);
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
