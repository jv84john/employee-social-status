import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IAttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';
import { AttachmentInfoEssService } from './attachment-info-ess.service';

@Component({
    selector: 'jhi-attachment-info-ess-update',
    templateUrl: './attachment-info-ess-update.component.html'
})
export class AttachmentInfoEssUpdateComponent implements OnInit {
    attachmentInfo: IAttachmentInfoEss;
    isSaving: boolean;

    constructor(protected attachmentInfoService: AttachmentInfoEssService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attachmentInfo }) => {
            this.attachmentInfo = attachmentInfo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.attachmentInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.attachmentInfoService.update(this.attachmentInfo));
        } else {
            this.subscribeToSaveResponse(this.attachmentInfoService.create(this.attachmentInfo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAttachmentInfoEss>>) {
        result.subscribe((res: HttpResponse<IAttachmentInfoEss>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
