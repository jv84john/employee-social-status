/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { PersonalDataEssDeleteDialogComponent } from 'app/entities/personal-data-ess/personal-data-ess-delete-dialog.component';
import { PersonalDataEssService } from 'app/entities/personal-data-ess/personal-data-ess.service';

describe('Component Tests', () => {
    describe('PersonalDataEss Management Delete Component', () => {
        let comp: PersonalDataEssDeleteDialogComponent;
        let fixture: ComponentFixture<PersonalDataEssDeleteDialogComponent>;
        let service: PersonalDataEssService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [PersonalDataEssDeleteDialogComponent]
            })
                .overrideTemplate(PersonalDataEssDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonalDataEssDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonalDataEssService);
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
