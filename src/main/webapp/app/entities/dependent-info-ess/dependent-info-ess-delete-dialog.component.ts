import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDependentInfoEss } from 'app/shared/model/dependent-info-ess.model';
import { DependentInfoEssService } from './dependent-info-ess.service';

@Component({
    selector: 'jhi-dependent-info-ess-delete-dialog',
    templateUrl: './dependent-info-ess-delete-dialog.component.html'
})
export class DependentInfoEssDeleteDialogComponent {
    dependentInfo: IDependentInfoEss;

    constructor(
        protected dependentInfoService: DependentInfoEssService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dependentInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dependentInfoListModification',
                content: 'Deleted an dependentInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dependent-info-ess-delete-popup',
    template: ''
})
export class DependentInfoEssDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dependentInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DependentInfoEssDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.dependentInfo = dependentInfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/dependent-info-ess', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/dependent-info-ess', { outlets: { popup: null } }]);
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
