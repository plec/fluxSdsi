/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefEnvironementDetailComponent } from 'app/entities/ref-environement/ref-environement-detail.component';
import { RefEnvironement } from 'app/shared/model/ref-environement.model';

describe('Component Tests', () => {
    describe('RefEnvironement Management Detail Component', () => {
        let comp: RefEnvironementDetailComponent;
        let fixture: ComponentFixture<RefEnvironementDetailComponent>;
        const route = ({ data: of({ refEnvironement: new RefEnvironement(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefEnvironementDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefEnvironementDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefEnvironementDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refEnvironement).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
