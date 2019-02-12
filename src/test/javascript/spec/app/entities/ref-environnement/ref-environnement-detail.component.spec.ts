/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefEnvironnementDetailComponent } from 'app/entities/ref-environnement/ref-environnement-detail.component';
import { RefEnvironnement } from 'app/shared/model/ref-environnement.model';

describe('Component Tests', () => {
    describe('RefEnvironnement Management Detail Component', () => {
        let comp: RefEnvironnementDetailComponent;
        let fixture: ComponentFixture<RefEnvironnementDetailComponent>;
        const route = ({ data: of({ refEnvironnement: new RefEnvironnement(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefEnvironnementDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefEnvironnementDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefEnvironnementDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refEnvironnement).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
