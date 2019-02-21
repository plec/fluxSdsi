/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefVlanUpdateComponent } from 'app/entities/ref-vlan/ref-vlan-update.component';
import { RefVlanService } from 'app/entities/ref-vlan/ref-vlan.service';
import { RefVlan } from 'app/shared/model/ref-vlan.model';

describe('Component Tests', () => {
    describe('RefVlan Management Update Component', () => {
        let comp: RefVlanUpdateComponent;
        let fixture: ComponentFixture<RefVlanUpdateComponent>;
        let service: RefVlanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefVlanUpdateComponent]
            })
                .overrideTemplate(RefVlanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefVlanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefVlanService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefVlan(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refVlan = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefVlan();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refVlan = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
