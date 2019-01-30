import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DependentInfoEss } from 'app/shared/model/dependent-info-ess.model';
import { DependentInfoEssService } from './dependent-info-ess.service';
import { DependentInfoEssComponent } from './dependent-info-ess.component';
import { DependentInfoEssDetailComponent } from './dependent-info-ess-detail.component';
import { DependentInfoEssUpdateComponent } from './dependent-info-ess-update.component';
import { DependentInfoEssDeletePopupComponent } from './dependent-info-ess-delete-dialog.component';
import { IDependentInfoEss } from 'app/shared/model/dependent-info-ess.model';

@Injectable({ providedIn: 'root' })
export class DependentInfoEssResolve implements Resolve<IDependentInfoEss> {
    constructor(private service: DependentInfoEssService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDependentInfoEss> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DependentInfoEss>) => response.ok),
                map((dependentInfo: HttpResponse<DependentInfoEss>) => dependentInfo.body)
            );
        }
        return of(new DependentInfoEss());
    }
}

export const dependentInfoRoute: Routes = [
    {
        path: '',
        component: DependentInfoEssComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'employeesocialstatusApp.dependentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DependentInfoEssDetailComponent,
        resolve: {
            dependentInfo: DependentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.dependentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DependentInfoEssUpdateComponent,
        resolve: {
            dependentInfo: DependentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.dependentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DependentInfoEssUpdateComponent,
        resolve: {
            dependentInfo: DependentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.dependentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dependentInfoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DependentInfoEssDeletePopupComponent,
        resolve: {
            dependentInfo: DependentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.dependentInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
