import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { EmployeesocialstatusSharedModule } from 'app/shared';
import {
    AttachmentInfoEssComponent,
    AttachmentInfoEssDetailComponent,
    AttachmentInfoEssUpdateComponent,
    AttachmentInfoEssDeletePopupComponent,
    AttachmentInfoEssDeleteDialogComponent,
    attachmentInfoRoute,
    attachmentInfoPopupRoute
} from './';

const ENTITY_STATES = [...attachmentInfoRoute, ...attachmentInfoPopupRoute];

@NgModule({
    imports: [EmployeesocialstatusSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttachmentInfoEssComponent,
        AttachmentInfoEssDetailComponent,
        AttachmentInfoEssUpdateComponent,
        AttachmentInfoEssDeleteDialogComponent,
        AttachmentInfoEssDeletePopupComponent
    ],
    entryComponents: [
        AttachmentInfoEssComponent,
        AttachmentInfoEssUpdateComponent,
        AttachmentInfoEssDeleteDialogComponent,
        AttachmentInfoEssDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EmployeesocialstatusAttachmentInfoEssModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
