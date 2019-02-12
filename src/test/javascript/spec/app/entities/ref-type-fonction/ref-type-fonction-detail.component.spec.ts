/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeFonctionDetailComponent } from 'app/entities/ref-type-fonction/ref-type-fonction-detail.component';
import { RefTypeFonction } from 'app/shared/model/ref-type-fonction.model';

describe('Component Tests', () => {
    describe('RefTypeFonction Management Detail Component', () => {
        let comp: RefTypeFonctionDetailComponent;
        let fixture: ComponentFixture<RefTypeFonctionDetailComponent>;
        const route = ({ data: of({ refTypeFonction: new RefTypeFonction(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeFonctionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefTypeFonctionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefTypeFonctionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refTypeFonction).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
