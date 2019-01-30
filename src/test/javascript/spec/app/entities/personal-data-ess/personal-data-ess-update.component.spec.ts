/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { PersonalDataEssUpdateComponent } from 'app/entities/personal-data-ess/personal-data-ess-update.component';
import { PersonalDataEssService } from 'app/entities/personal-data-ess/personal-data-ess.service';
import { PersonalDataEss } from 'app/shared/model/personal-data-ess.model';

describe('Component Tests', () => {
    describe('PersonalDataEss Management Update Component', () => {
        let comp: PersonalDataEssUpdateComponent;
        let fixture: ComponentFixture<PersonalDataEssUpdateComponent>;
        let service: PersonalDataEssService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [PersonalDataEssUpdateComponent]
            })
                .overrideTemplate(PersonalDataEssUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PersonalDataEssUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonalDataEssService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PersonalDataEss(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.personalData = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PersonalDataEss();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.personalData = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
