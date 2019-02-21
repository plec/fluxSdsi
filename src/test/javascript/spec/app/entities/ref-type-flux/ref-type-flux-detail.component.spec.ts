/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeFluxDetailComponent } from 'app/entities/ref-type-flux/ref-type-flux-detail.component';
import { RefTypeFlux } from 'app/shared/model/ref-type-flux.model';

describe('Component Tests', () => {
    describe('RefTypeFlux Management Detail Component', () => {
        let comp: RefTypeFluxDetailComponent;
        let fixture: ComponentFixture<RefTypeFluxDetailComponent>;
        const route = ({ data: of({ refTypeFlux: new RefTypeFlux(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeFluxDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefTypeFluxDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefTypeFluxDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refTypeFlux).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
