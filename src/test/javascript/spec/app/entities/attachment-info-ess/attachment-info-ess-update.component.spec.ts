/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { AttachmentInfoEssUpdateComponent } from 'app/entities/attachment-info-ess/attachment-info-ess-update.component';
import { AttachmentInfoEssService } from 'app/entities/attachment-info-ess/attachment-info-ess.service';
import { AttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';

describe('Component Tests', () => {
    describe('AttachmentInfoEss Management Update Component', () => {
        let comp: AttachmentInfoEssUpdateComponent;
        let fixture: ComponentFixture<AttachmentInfoEssUpdateComponent>;
        let service: AttachmentInfoEssService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [AttachmentInfoEssUpdateComponent]
            })
                .overrideTemplate(AttachmentInfoEssUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttachmentInfoEssUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttachmentInfoEssService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AttachmentInfoEss(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attachmentInfo = entity;
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
                    const entity = new AttachmentInfoEss();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attachmentInfo = entity;
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
