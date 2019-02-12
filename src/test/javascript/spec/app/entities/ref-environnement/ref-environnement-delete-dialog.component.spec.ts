/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefEnvironnementDeleteDialogComponent } from 'app/entities/ref-environnement/ref-environnement-delete-dialog.component';
import { RefEnvironnementService } from 'app/entities/ref-environnement/ref-environnement.service';

describe('Component Tests', () => {
    describe('RefEnvironnement Management Delete Component', () => {
        let comp: RefEnvironnementDeleteDialogComponent;
        let fixture: ComponentFixture<RefEnvironnementDeleteDialogComponent>;
        let service: RefEnvironnementService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefEnvironnementDeleteDialogComponent]
            })
                .overrideTemplate(RefEnvironnementDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefEnvironnementDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefEnvironnementService);
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
