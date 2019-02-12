/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefZoneDetailComponent } from 'app/entities/ref-zone/ref-zone-detail.component';
import { RefZone } from 'app/shared/model/ref-zone.model';

describe('Component Tests', () => {
    describe('RefZone Management Detail Component', () => {
        let comp: RefZoneDetailComponent;
        let fixture: ComponentFixture<RefZoneDetailComponent>;
        const route = ({ data: of({ refZone: new RefZone(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefZoneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefZoneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefZoneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refZone).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
