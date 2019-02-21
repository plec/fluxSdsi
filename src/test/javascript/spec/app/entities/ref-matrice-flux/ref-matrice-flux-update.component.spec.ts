/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefMatriceFluxUpdateComponent } from 'app/entities/ref-matrice-flux/ref-matrice-flux-update.component';
import { RefMatriceFluxService } from 'app/entities/ref-matrice-flux/ref-matrice-flux.service';
import { RefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

describe('Component Tests', () => {
    describe('RefMatriceFlux Management Update Component', () => {
        let comp: RefMatriceFluxUpdateComponent;
        let fixture: ComponentFixture<RefMatriceFluxUpdateComponent>;
        let service: RefMatriceFluxService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefMatriceFluxUpdateComponent]
            })
                .overrideTemplate(RefMatriceFluxUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefMatriceFluxUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefMatriceFluxService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefMatriceFlux(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refMatriceFlux = entity;
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
                    const entity = new RefMatriceFlux();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refMatriceFlux = entity;
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
