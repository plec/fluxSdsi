/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefEnvironementUpdateComponent } from 'app/entities/ref-environement/ref-environement-update.component';
import { RefEnvironementService } from 'app/entities/ref-environement/ref-environement.service';
import { RefEnvironement } from 'app/shared/model/ref-environement.model';

describe('Component Tests', () => {
    describe('RefEnvironement Management Update Component', () => {
        let comp: RefEnvironementUpdateComponent;
        let fixture: ComponentFixture<RefEnvironementUpdateComponent>;
        let service: RefEnvironementService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefEnvironementUpdateComponent]
            })
                .overrideTemplate(RefEnvironementUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefEnvironementUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefEnvironementService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefEnvironement(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refEnvironement = entity;
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
                    const entity = new RefEnvironement();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refEnvironement = entity;
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
