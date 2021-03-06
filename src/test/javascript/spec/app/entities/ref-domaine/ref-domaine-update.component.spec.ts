/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefDomaineUpdateComponent } from 'app/entities/ref-domaine/ref-domaine-update.component';
import { RefDomaineService } from 'app/entities/ref-domaine/ref-domaine.service';
import { RefDomaine } from 'app/shared/model/ref-domaine.model';

describe('Component Tests', () => {
    describe('RefDomaine Management Update Component', () => {
        let comp: RefDomaineUpdateComponent;
        let fixture: ComponentFixture<RefDomaineUpdateComponent>;
        let service: RefDomaineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefDomaineUpdateComponent]
            })
                .overrideTemplate(RefDomaineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefDomaineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefDomaineService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefDomaine(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refDomaine = entity;
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
                    const entity = new RefDomaine();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refDomaine = entity;
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
