/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefFluxUpdateComponent } from 'app/entities/ref-flux/ref-flux-update.component';
import { RefFluxService } from 'app/entities/ref-flux/ref-flux.service';
import { RefFlux } from 'app/shared/model/ref-flux.model';

describe('Component Tests', () => {
    describe('RefFlux Management Update Component', () => {
        let comp: RefFluxUpdateComponent;
        let fixture: ComponentFixture<RefFluxUpdateComponent>;
        let service: RefFluxService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefFluxUpdateComponent]
            })
                .overrideTemplate(RefFluxUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefFluxUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefFluxService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefFlux(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refFlux = entity;
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
                    const entity = new RefFlux();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refFlux = entity;
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
