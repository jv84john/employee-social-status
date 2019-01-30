import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPersonalDataEss } from 'app/shared/model/personal-data-ess.model';
import { PersonalDataEssService } from './personal-data-ess.service';
import { IAttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';
import { AttachmentInfoEssService } from 'app/entities/attachment-info-ess';

@Component({
    selector: 'jhi-personal-data-ess-update',
    templateUrl: './personal-data-ess-update.component.html'
})
export class PersonalDataEssUpdateComponent implements OnInit {
    personalData: IPersonalDataEss;
    isSaving: boolean;

    attachmentinfos: IAttachmentInfoEss[];
    appointmentDateDp: any;
    createdAt: string;
    modifiedAt: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected personalDataService: PersonalDataEssService,
        protected attachmentInfoService: AttachmentInfoEssService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ personalData }) => {
            this.personalData = personalData;
            this.createdAt = this.personalData.createdAt != null ? this.personalData.createdAt.format(DATE_TIME_FORMAT) : null;
            this.modifiedAt = this.personalData.modifiedAt != null ? this.personalData.modifiedAt.format(DATE_TIME_FORMAT) : null;
        });
        this.attachmentInfoService
            .query({ filter: 'personaldata-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IAttachmentInfoEss[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAttachmentInfoEss[]>) => response.body)
            )
            .subscribe(
                (res: IAttachmentInfoEss[]) => {
                    if (!this.personalData.attachmentInfoId) {
                        this.attachmentinfos = res;
                    } else {
                        this.attachmentInfoService
                            .find(this.personalData.attachmentInfoId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IAttachmentInfoEss>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IAttachmentInfoEss>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IAttachmentInfoEss) => (this.attachmentinfos = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.personalData.createdAt = this.createdAt != null ? moment(this.createdAt, DATE_TIME_FORMAT) : null;
        this.personalData.modifiedAt = this.modifiedAt != null ? moment(this.modifiedAt, DATE_TIME_FORMAT) : null;
        if (this.personalData.id !== undefined) {
            this.subscribeToSaveResponse(this.personalDataService.update(this.personalData));
        } else {
            this.subscribeToSaveResponse(this.personalDataService.create(this.personalData));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonalDataEss>>) {
        result.subscribe((res: HttpResponse<IPersonalDataEss>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAttachmentInfoById(index: number, item: IAttachmentInfoEss) {
        return item.id;
    }
}
