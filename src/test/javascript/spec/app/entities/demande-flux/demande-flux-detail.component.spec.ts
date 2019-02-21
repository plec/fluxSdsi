/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { DemandeFluxDetailComponent } from 'app/entities/demande-flux/demande-flux-detail.component';
import { DemandeFlux } from 'app/shared/model/demande-flux.model';

describe('Component Tests', () => {
    describe('DemandeFlux Management Detail Component', () => {
        let comp: DemandeFluxDetailComponent;
        let fixture: ComponentFixture<DemandeFluxDetailComponent>;
        const route = ({ data: of({ demandeFlux: new DemandeFlux(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [DemandeFluxDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DemandeFluxDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DemandeFluxDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.demandeFlux).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
