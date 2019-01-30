/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EmployeesocialstatusTestModule } from '../../../test.module';
import { DependentInfoEssDeleteDialogComponent } from 'app/entities/dependent-info-ess/dependent-info-ess-delete-dialog.component';
import { DependentInfoEssService } from 'app/entities/dependent-info-ess/dependent-info-ess.service';

describe('Component Tests', () => {
    describe('DependentInfoEss Management Delete Component', () => {
        let comp: DependentInfoEssDeleteDialogComponent;
        let fixture: ComponentFixture<DependentInfoEssDeleteDialogComponent>;
        let service: DependentInfoEssService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [EmployeesocialstatusTestModule],
                declarations: [DependentInfoEssDeleteDialogComponent]
            })
                .overrideTemplate(DependentInfoEssDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DependentInfoEssDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DependentInfoEssService);
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
