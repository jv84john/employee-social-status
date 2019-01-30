import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PersonalDataEss } from 'app/shared/model/personal-data-ess.model';
import { PersonalDataEssService } from './personal-data-ess.service';
import { PersonalDataEssComponent } from './personal-data-ess.component';
import { PersonalDataEssDetailComponent } from './personal-data-ess-detail.component';
import { PersonalDataEssUpdateComponent } from './personal-data-ess-update.component';
import { PersonalDataEssDeletePopupComponent } from './personal-data-ess-delete-dialog.component';
import { IPersonalDataEss } from 'app/shared/model/personal-data-ess.model';

@Injectable({ providedIn: 'root' })
export class PersonalDataEssResolve implements Resolve<IPersonalDataEss> {
    constructor(private service: PersonalDataEssService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPersonalDataEss> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PersonalDataEss>) => response.ok),
                map((personalData: HttpResponse<PersonalDataEss>) => personalData.body)
            );
        }
        return of(new PersonalDataEss());
    }
}

export const personalDataRoute: Routes = [
    {
        path: '',
        component: PersonalDataEssComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'employeesocialstatusApp.personalData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PersonalDataEssDetailComponent,
        resolve: {
            personalData: PersonalDataEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.personalData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PersonalDataEssUpdateComponent,
        resolve: {
            personalData: PersonalDataEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.personalData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PersonalDataEssUpdateComponent,
        resolve: {
            personalData: PersonalDataEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.personalData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personalDataPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PersonalDataEssDeletePopupComponent,
        resolve: {
            personalData: PersonalDataEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.personalData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
