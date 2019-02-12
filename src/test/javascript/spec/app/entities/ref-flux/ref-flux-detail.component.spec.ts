/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefFluxDetailComponent } from 'app/entities/ref-flux/ref-flux-detail.component';
import { RefFlux } from 'app/shared/model/ref-flux.model';

describe('Component Tests', () => {
    describe('RefFlux Management Detail Component', () => {
        let comp: RefFluxDetailComponent;
        let fixture: ComponentFixture<RefFluxDetailComponent>;
        const route = ({ data: of({ refFlux: new RefFlux(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefFluxDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefFluxDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefFluxDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refFlux).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
