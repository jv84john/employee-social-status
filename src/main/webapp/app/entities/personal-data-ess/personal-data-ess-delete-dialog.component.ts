import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonalDataEss } from 'app/shared/model/personal-data-ess.model';
import { PersonalDataEssService } from './personal-data-ess.service';

@Component({
    selector: 'jhi-personal-data-ess-delete-dialog',
    templateUrl: './personal-data-ess-delete-dialog.component.html'
})
export class PersonalDataEssDeleteDialogComponent {
    personalData: IPersonalDataEss;

    constructor(
        protected personalDataService: PersonalDataEssService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.personalDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'personalDataListModification',
                content: 'Deleted an personalData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-personal-data-ess-delete-popup',
    template: ''
})
export class PersonalDataEssDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personalData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PersonalDataEssDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.personalData = personalData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/personal-data-ess', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/personal-data-ess', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
