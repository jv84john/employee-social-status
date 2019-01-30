/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { AttachmentInfoEssDeleteDialogComponent } from 'app/entities/attachment-info-ess/attachment-info-ess-delete-dialog.component';
import { AttachmentInfoEssService } from 'app/entities/attachment-info-ess/attachment-info-ess.service';

describe('Component Tests', () => {
    describe('AttachmentInfoEss Management Delete Component', () => {
        let comp: AttachmentInfoEssDeleteDialogComponent;
        let fixture: ComponentFixture<AttachmentInfoEssDeleteDialogComponent>;
        let service: AttachmentInfoEssService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [AttachmentInfoEssDeleteDialogComponent]
            })
                .overrideTemplate(AttachmentInfoEssDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttachmentInfoEssDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttachmentInfoEssService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
