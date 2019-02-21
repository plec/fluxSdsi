/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefTypeAuthorisationDetailComponent } from 'app/entities/ref-type-authorisation/ref-type-authorisation-detail.component';
import { RefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';

describe('Component Tests', () => {
    describe('RefTypeAuthorisation Management Detail Component', () => {
        let comp: RefTypeAuthorisationDetailComponent;
        let fixture: ComponentFixture<RefTypeAuthorisationDetailComponent>;
        const route = ({ data: of({ refTypeAuthorisation: new RefTypeAuthorisation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefTypeAuthorisationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefTypeAuthorisationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefTypeAuthorisationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refTypeAuthorisation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
