/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefDomaineDetailComponent } from 'app/entities/ref-domaine/ref-domaine-detail.component';
import { RefDomaine } from 'app/shared/model/ref-domaine.model';

describe('Component Tests', () => {
    describe('RefDomaine Management Detail Component', () => {
        let comp: RefDomaineDetailComponent;
        let fixture: ComponentFixture<RefDomaineDetailComponent>;
        const route = ({ data: of({ refDomaine: new RefDomaine(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefDomaineDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefDomaineDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefDomaineDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refDomaine).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
