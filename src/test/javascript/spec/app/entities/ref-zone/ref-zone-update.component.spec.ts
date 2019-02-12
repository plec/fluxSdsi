/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefZoneUpdateComponent } from 'app/entities/ref-zone/ref-zone-update.component';
import { RefZoneService } from 'app/entities/ref-zone/ref-zone.service';
import { RefZone } from 'app/shared/model/ref-zone.model';

describe('Component Tests', () => {
    describe('RefZone Management Update Component', () => {
        let comp: RefZoneUpdateComponent;
        let fixture: ComponentFixture<RefZoneUpdateComponent>;
        let service: RefZoneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefZoneUpdateComponent]
            })
                .overrideTemplate(RefZoneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefZoneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefZoneService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefZone(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refZone = entity;
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
                    const entity = new RefZone();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refZone = entity;
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
