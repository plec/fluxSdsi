/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefVlanDeleteDialogComponent } from 'app/entities/ref-vlan/ref-vlan-delete-dialog.component';
import { RefVlanService } from 'app/entities/ref-vlan/ref-vlan.service';

describe('Component Tests', () => {
    describe('RefVlan Management Delete Component', () => {
        let comp: RefVlanDeleteDialogComponent;
        let fixture: ComponentFixture<RefVlanDeleteDialogComponent>;
        let service: RefVlanService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefVlanDeleteDialogComponent]
            })
                .overrideTemplate(RefVlanDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefVlanDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefVlanService);
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
