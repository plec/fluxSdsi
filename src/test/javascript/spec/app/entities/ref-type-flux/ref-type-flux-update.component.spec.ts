/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeFluxUpdateComponent } from 'app/entities/ref-type-flux/ref-type-flux-update.component';
import { RefTypeFluxService } from 'app/entities/ref-type-flux/ref-type-flux.service';
import { RefTypeFlux } from 'app/shared/model/ref-type-flux.model';

describe('Component Tests', () => {
    describe('RefTypeFlux Management Update Component', () => {
        let comp: RefTypeFluxUpdateComponent;
        let fixture: ComponentFixture<RefTypeFluxUpdateComponent>;
        let service: RefTypeFluxService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeFluxUpdateComponent]
            })
                .overrideTemplate(RefTypeFluxUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefTypeFluxUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeFluxService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefTypeFlux(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refTypeFlux = entity;
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
                    const entity = new RefTypeFlux();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refTypeFlux = entity;
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
