/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeAuthorisationUpdateComponent } from 'app/entities/ref-type-authorisation/ref-type-authorisation-update.component';
import { RefTypeAuthorisationService } from 'app/entities/ref-type-authorisation/ref-type-authorisation.service';
import { RefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';

describe('Component Tests', () => {
    describe('RefTypeAuthorisation Management Update Component', () => {
        let comp: RefTypeAuthorisationUpdateComponent;
        let fixture: ComponentFixture<RefTypeAuthorisationUpdateComponent>;
        let service: RefTypeAuthorisationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeAuthorisationUpdateComponent]
            })
                .overrideTemplate(RefTypeAuthorisationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefTypeAuthorisationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeAuthorisationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefTypeAuthorisation(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refTypeAuthorisation = entity;
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
                    const entity = new RefTypeAuthorisation();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refTypeAuthorisation = entity;
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
