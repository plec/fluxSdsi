/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefFonctionDetailComponent } from 'app/entities/ref-fonction/ref-fonction-detail.component';
import { RefFonction } from 'app/shared/model/ref-fonction.model';

describe('Component Tests', () => {
    describe('RefFonction Management Detail Component', () => {
        let comp: RefFonctionDetailComponent;
        let fixture: ComponentFixture<RefFonctionDetailComponent>;
        const route = ({ data: of({ refFonction: new RefFonction(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefFonctionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefFonctionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefFonctionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refFonction).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
