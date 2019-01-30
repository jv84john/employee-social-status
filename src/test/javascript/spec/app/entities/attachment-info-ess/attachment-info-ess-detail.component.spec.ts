/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { AttachmentInfoEssDetailComponent } from 'app/entities/attachment-info-ess/attachment-info-ess-detail.component';
import { AttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';

describe('Component Tests', () => {
    describe('AttachmentInfoEss Management Detail Component', () => {
        let comp: AttachmentInfoEssDetailComponent;
        let fixture: ComponentFixture<AttachmentInfoEssDetailComponent>;
        const route = ({ data: of({ attachmentInfo: new AttachmentInfoEss(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [AttachmentInfoEssDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttachmentInfoEssDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttachmentInfoEssDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attachmentInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
