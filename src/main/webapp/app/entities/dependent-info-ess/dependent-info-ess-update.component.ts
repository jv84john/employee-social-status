import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IDependentInfoEss } from 'app/shared/model/dependent-info-ess.model';
import { DependentInfoEssService } from './dependent-info-ess.service';
import { IPersonalDataEss } from 'app/shared/model/personal-data-ess.model';
import { PersonalDataEssService } from 'app/entities/personal-data-ess';

@Component({
    selector: 'jhi-dependent-info-ess-update',
    templateUrl: './dependent-info-ess-update.component.html'
})
export class DependentInfoEssUpdateComponent implements OnInit {
    dependentInfo: IDependentInfoEss;
    isSaving: boolean;

    personaldata: IPersonalDataEss[];
    marriageDateDp: any;
    hireDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected dependentInfoService: DependentInfoEssService,
        protected personalDataService: PersonalDataEssService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dependentInfo }) => {
            this.dependentInfo = dependentInfo;
        });
        this.personalDataService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPersonalDataEss[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPersonalDataEss[]>) => response.body)
            )
            .subscribe((res: IPersonalDataEss[]) => (this.personaldata = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dependentInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.dependentInfoService.update(this.dependentInfo));
        } else {
            this.subscribeToSaveResponse(this.dependentInfoService.create(this.dependentInfo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDependentInfoEss>>) {
        result.subscribe((res: HttpResponse<IDependentInfoEss>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPersonalDataById(index: number, item: IPersonalDataEss) {
        return item.id;
    }
}
