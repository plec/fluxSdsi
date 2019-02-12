/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefNumeroDetailComponent } from 'app/entities/ref-numero/ref-numero-detail.component';
import { RefNumero } from 'app/shared/model/ref-numero.model';

describe('Component Tests', () => {
    describe('RefNumero Management Detail Component', () => {
        let comp: RefNumeroDetailComponent;
        let fixture: ComponentFixture<RefNumeroDetailComponent>;
        const route = ({ data: of({ refNumero: new RefNumero(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefNumeroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefNumeroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefNumeroDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refNumero).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
