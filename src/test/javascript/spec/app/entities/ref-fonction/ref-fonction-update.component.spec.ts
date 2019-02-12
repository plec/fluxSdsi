/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefFonctionUpdateComponent } from 'app/entities/ref-fonction/ref-fonction-update.component';
import { RefFonctionService } from 'app/entities/ref-fonction/ref-fonction.service';
import { RefFonction } from 'app/shared/model/ref-fonction.model';

describe('Component Tests', () => {
    describe('RefFonction Management Update Component', () => {
        let comp: RefFonctionUpdateComponent;
        let fixture: ComponentFixture<RefFonctionUpdateComponent>;
        let service: RefFonctionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefFonctionUpdateComponent]
            })
                .overrideTemplate(RefFonctionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefFonctionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefFonctionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefFonction(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refFonction = entity;
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
                    const entity = new RefFonction();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refFonction = entity;
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
