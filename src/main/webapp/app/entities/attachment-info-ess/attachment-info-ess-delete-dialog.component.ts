import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';
import { AttachmentInfoEssService } from './attachment-info-ess.service';

@Component({
    selector: 'jhi-attachment-info-ess-delete-dialog',
    templateUrl: './attachment-info-ess-delete-dialog.component.html'
})
export class AttachmentInfoEssDeleteDialogComponent {
    attachmentInfo: IAttachmentInfoEss;

    constructor(
        protected attachmentInfoService: AttachmentInfoEssService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attachmentInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attachmentInfoListModification',
                content: 'Deleted an attachmentInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attachment-info-ess-delete-popup',
    template: ''
})
export class AttachmentInfoEssDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attachmentInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttachmentInfoEssDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.attachmentInfo = attachmentInfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/attachment-info-ess', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/attachment-info-ess', { outlets: { popup: null } }]);
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
