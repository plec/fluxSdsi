/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefEnvironnementUpdateComponent } from 'app/entities/ref-environnement/ref-environnement-update.component';
import { RefEnvironnementService } from 'app/entities/ref-environnement/ref-environnement.service';
import { RefEnvironnement } from 'app/shared/model/ref-environnement.model';

describe('Component Tests', () => {
    describe('RefEnvironnement Management Update Component', () => {
        let comp: RefEnvironnementUpdateComponent;
        let fixture: ComponentFixture<RefEnvironnementUpdateComponent>;
        let service: RefEnvironnementService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefEnvironnementUpdateComponent]
            })
                .overrideTemplate(RefEnvironnementUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefEnvironnementUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefEnvironnementService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefEnvironnement(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refEnvironnement = entity;
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
                    const entity = new RefEnvironnement();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refEnvironnement = entity;
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
