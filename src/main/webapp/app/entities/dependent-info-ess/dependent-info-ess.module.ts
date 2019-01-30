import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { EmployeesocialstatusSharedModule } from 'app/shared';
import {
    DependentInfoEssComponent,
    DependentInfoEssDetailComponent,
    DependentInfoEssUpdateComponent,
    DependentInfoEssDeletePopupComponent,
    DependentInfoEssDeleteDialogComponent,
    dependentInfoRoute,
    dependentInfoPopupRoute
} from './';

const ENTITY_STATES = [...dependentInfoRoute, ...dependentInfoPopupRoute];

@NgModule({
    imports: [EmployeesocialstatusSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DependentInfoEssComponent,
        DependentInfoEssDetailComponent,
        DependentInfoEssUpdateComponent,
        DependentInfoEssDeleteDialogComponent,
        DependentInfoEssDeletePopupComponent
    ],
    entryComponents: [
        DependentInfoEssComponent,
        DependentInfoEssUpdateComponent,
        DependentInfoEssDeleteDialogComponent,
        DependentInfoEssDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EmployeesocialstatusDependentInfoEssModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
