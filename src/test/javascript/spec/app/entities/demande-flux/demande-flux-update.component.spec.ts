/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { DemandeFluxUpdateComponent } from 'app/entities/demande-flux/demande-flux-update.component';
import { DemandeFluxService } from 'app/entities/demande-flux/demande-flux.service';
import { DemandeFlux } from 'app/shared/model/demande-flux.model';

describe('Component Tests', () => {
    describe('DemandeFlux Management Update Component', () => {
        let comp: DemandeFluxUpdateComponent;
        let fixture: ComponentFixture<DemandeFluxUpdateComponent>;
        let service: DemandeFluxService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [DemandeFluxUpdateComponent]
            })
                .overrideTemplate(DemandeFluxUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DemandeFluxUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DemandeFluxService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DemandeFlux(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.demandeFlux = entity;
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
                    const entity = new DemandeFlux();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.demandeFlux = entity;
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
