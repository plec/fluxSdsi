/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeFonctionUpdateComponent } from 'app/entities/ref-type-fonction/ref-type-fonction-update.component';
import { RefTypeFonctionService } from 'app/entities/ref-type-fonction/ref-type-fonction.service';
import { RefTypeFonction } from 'app/shared/model/ref-type-fonction.model';

describe('Component Tests', () => {
    describe('RefTypeFonction Management Update Component', () => {
        let comp: RefTypeFonctionUpdateComponent;
        let fixture: ComponentFixture<RefTypeFonctionUpdateComponent>;
        let service: RefTypeFonctionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeFonctionUpdateComponent]
            })
                .overrideTemplate(RefTypeFonctionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefTypeFonctionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeFonctionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefTypeFonction(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refTypeFonction = entity;
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
                    const entity = new RefTypeFonction();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refTypeFonction = entity;
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
