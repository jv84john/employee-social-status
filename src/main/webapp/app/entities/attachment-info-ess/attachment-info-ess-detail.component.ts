import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';

@Component({
    selector: 'jhi-attachment-info-ess-detail',
    templateUrl: './attachment-info-ess-detail.component.html'
})
export class AttachmentInfoEssDetailComponent implements OnInit {
    attachmentInfo: IAttachmentInfoEss;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attachmentInfo }) => {
            this.attachmentInfo = attachmentInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
