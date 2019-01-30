/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { PersonalDataEssDetailComponent } from 'app/entities/personal-data-ess/personal-data-ess-detail.component';
import { PersonalDataEss } from 'app/shared/model/personal-data-ess.model';

describe('Component Tests', () => {
    describe('PersonalDataEss Management Detail Component', () => {
        let comp: PersonalDataEssDetailComponent;
        let fixture: ComponentFixture<PersonalDataEssDetailComponent>;
        const route = ({ data: of({ personalData: new PersonalDataEss(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [PersonalDataEssDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PersonalDataEssDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonalDataEssDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.personalData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
