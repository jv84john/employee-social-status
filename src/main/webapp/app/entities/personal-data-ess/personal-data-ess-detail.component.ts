import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonalDataEss } from 'app/shared/model/personal-data-ess.model';

@Component({
    selector: 'jhi-personal-data-ess-detail',
    templateUrl: './personal-data-ess-detail.component.html'
})
export class PersonalDataEssDetailComponent implements OnInit {
    personalData: IPersonalDataEss;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ personalData }) => {
            this.personalData = personalData;
        });
    }

    previousState() {
        window.history.back();
    }
}
