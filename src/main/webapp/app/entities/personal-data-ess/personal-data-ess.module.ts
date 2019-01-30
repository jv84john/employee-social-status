import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { EmployeesocialstatusSharedModule } from 'app/shared';
import {
    PersonalDataEssComponent,
    PersonalDataEssDetailComponent,
    PersonalDataEssUpdateComponent,
    PersonalDataEssDeletePopupComponent,
    PersonalDataEssDeleteDialogComponent,
    personalDataRoute,
    personalDataPopupRoute
} from './';

const ENTITY_STATES = [...personalDataRoute, ...personalDataPopupRoute];

@NgModule({
    imports: [EmployeesocialstatusSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PersonalDataEssComponent,
        PersonalDataEssDetailComponent,
        PersonalDataEssUpdateComponent,
        PersonalDataEssDeleteDialogComponent,
        PersonalDataEssDeletePopupComponent
    ],
    entryComponents: [
        PersonalDataEssComponent,
        PersonalDataEssUpdateComponent,
        PersonalDataEssDeleteDialogComponent,
        PersonalDataEssDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EmployeesocialstatusPersonalDataEssModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
