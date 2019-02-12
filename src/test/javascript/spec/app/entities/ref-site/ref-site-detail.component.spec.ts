/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefSiteDetailComponent } from 'app/entities/ref-site/ref-site-detail.component';
import { RefSite } from 'app/shared/model/ref-site.model';

describe('Component Tests', () => {
    describe('RefSite Management Detail Component', () => {
        let comp: RefSiteDetailComponent;
        let fixture: ComponentFixture<RefSiteDetailComponent>;
        const route = ({ data: of({ refSite: new RefSite(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefSiteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefSiteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefSiteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refSite).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
