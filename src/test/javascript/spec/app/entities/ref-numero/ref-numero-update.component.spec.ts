/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefNumeroUpdateComponent } from 'app/entities/ref-numero/ref-numero-update.component';
import { RefNumeroService } from 'app/entities/ref-numero/ref-numero.service';
import { RefNumero } from 'app/shared/model/ref-numero.model';

describe('Component Tests', () => {
    describe('RefNumero Management Update Component', () => {
        let comp: RefNumeroUpdateComponent;
        let fixture: ComponentFixture<RefNumeroUpdateComponent>;
        let service: RefNumeroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefNumeroUpdateComponent]
            })
                .overrideTemplate(RefNumeroUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefNumeroUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefNumeroService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefNumero(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refNumero = entity;
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
                    const entity = new RefNumero();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refNumero = entity;
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
