/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefMatriceFluxDetailComponent } from 'app/entities/ref-matrice-flux/ref-matrice-flux-detail.component';
import { RefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

describe('Component Tests', () => {
    describe('RefMatriceFlux Management Detail Component', () => {
        let comp: RefMatriceFluxDetailComponent;
        let fixture: ComponentFixture<RefMatriceFluxDetailComponent>;
        const route = ({ data: of({ refMatriceFlux: new RefMatriceFlux(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefMatriceFluxDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefMatriceFluxDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefMatriceFluxDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refMatriceFlux).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
