/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { DependentInfoEssUpdateComponent } from 'app/entities/dependent-info-ess/dependent-info-ess-update.component';
import { DependentInfoEssService } from 'app/entities/dependent-info-ess/dependent-info-ess.service';
import { DependentInfoEss } from 'app/shared/model/dependent-info-ess.model';

describe('Component Tests', () => {
    describe('DependentInfoEss Management Update Component', () => {
        let comp: DependentInfoEssUpdateComponent;
        let fixture: ComponentFixture<DependentInfoEssUpdateComponent>;
        let service: DependentInfoEssService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [DependentInfoEssUpdateComponent]
            })
                .overrideTemplate(DependentInfoEssUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DependentInfoEssUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DependentInfoEssService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DependentInfoEss(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dependentInfo = entity;
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
                    const entity = new DependentInfoEss();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dependentInfo = entity;
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
