import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDependentInfoEss } from 'app/shared/model/dependent-info-ess.model';

@Component({
    selector: 'jhi-dependent-info-ess-detail',
    templateUrl: './dependent-info-ess-detail.component.html'
})
export class DependentInfoEssDetailComponent implements OnInit {
    dependentInfo: IDependentInfoEss;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dependentInfo }) => {
            this.dependentInfo = dependentInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
