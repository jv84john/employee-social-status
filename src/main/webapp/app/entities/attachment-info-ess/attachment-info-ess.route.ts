import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';
import { AttachmentInfoEssService } from './attachment-info-ess.service';
import { AttachmentInfoEssComponent } from './attachment-info-ess.component';
import { AttachmentInfoEssDetailComponent } from './attachment-info-ess-detail.component';
import { AttachmentInfoEssUpdateComponent } from './attachment-info-ess-update.component';
import { AttachmentInfoEssDeletePopupComponent } from './attachment-info-ess-delete-dialog.component';
import { IAttachmentInfoEss } from 'app/shared/model/attachment-info-ess.model';

@Injectable({ providedIn: 'root' })
export class AttachmentInfoEssResolve implements Resolve<IAttachmentInfoEss> {
    constructor(private service: AttachmentInfoEssService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAttachmentInfoEss> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AttachmentInfoEss>) => response.ok),
                map((attachmentInfo: HttpResponse<AttachmentInfoEss>) => attachmentInfo.body)
            );
        }
        return of(new AttachmentInfoEss());
    }
}

export const attachmentInfoRoute: Routes = [
    {
        path: '',
        component: AttachmentInfoEssComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'employeesocialstatusApp.attachmentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AttachmentInfoEssDetailComponent,
        resolve: {
            attachmentInfo: AttachmentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.attachmentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AttachmentInfoEssUpdateComponent,
        resolve: {
            attachmentInfo: AttachmentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.attachmentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AttachmentInfoEssUpdateComponent,
        resolve: {
            attachmentInfo: AttachmentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.attachmentInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attachmentInfoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AttachmentInfoEssDeletePopupComponent,
        resolve: {
            attachmentInfo: AttachmentInfoEssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'employeesocialstatusApp.attachmentInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
