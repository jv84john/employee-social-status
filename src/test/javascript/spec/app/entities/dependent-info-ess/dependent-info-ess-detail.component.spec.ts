/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { DependentInfoEssDetailComponent } from 'app/entities/dependent-info-ess/dependent-info-ess-detail.component';
import { DependentInfoEss } from 'app/shared/model/dependent-info-ess.model';

describe('Component Tests', () => {
    describe('DependentInfoEss Management Detail Component', () => {
        let comp: DependentInfoEssDetailComponent;
        let fixture: ComponentFixture<DependentInfoEssDetailComponent>;
        const route = ({ data: of({ dependentInfo: new DependentInfoEss(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [DependentInfoEssDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DependentInfoEssDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DependentInfoEssDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dependentInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
