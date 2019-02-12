/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefSiteUpdateComponent } from 'app/entities/ref-site/ref-site-update.component';
import { RefSiteService } from 'app/entities/ref-site/ref-site.service';
import { RefSite } from 'app/shared/model/ref-site.model';

describe('Component Tests', () => {
    describe('RefSite Management Update Component', () => {
        let comp: RefSiteUpdateComponent;
        let fixture: ComponentFixture<RefSiteUpdateComponent>;
        let service: RefSiteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefSiteUpdateComponent]
            })
                .overrideTemplate(RefSiteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RefSiteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefSiteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RefSite(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refSite = entity;
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
                    const entity = new RefSite();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.refSite = entity;
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
