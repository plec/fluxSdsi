/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FluxSdsiTestModule } from '../../../test.module';
import { RefVlanDetailComponent } from 'app/entities/ref-vlan/ref-vlan-detail.component';
import { RefVlan } from 'app/shared/model/ref-vlan.model';

describe('Component Tests', () => {
    describe('RefVlan Management Detail Component', () => {
        let comp: RefVlanDetailComponent;
        let fixture: ComponentFixture<RefVlanDetailComponent>;
        const route = ({ data: of({ refVlan: new RefVlan(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FluxSdsiTestModule],
                declarations: [RefVlanDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RefVlanDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RefVlanDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.refVlan).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
