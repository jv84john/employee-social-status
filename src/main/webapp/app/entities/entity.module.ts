import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'personal-data-ess',
                loadChildren: './personal-data-ess/personal-data-ess.module#EmployeesocialstatusPersonalDataEssModule'
            },
            {
                path: 'dependent-info-ess',
                loadChildren: './dependent-info-ess/dependent-info-ess.module#EmployeesocialstatusDependentInfoEssModule'
            },
            {
                path: 'attachment-info-ess',
                loadChildren: './attachment-info-ess/attachment-info-ess.module#EmployeesocialstatusAttachmentInfoEssModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EmployeesocialstatusEntityModule {}
