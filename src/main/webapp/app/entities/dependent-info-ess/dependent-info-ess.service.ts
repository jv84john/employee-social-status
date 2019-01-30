import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDependentInfoEss } from 'app/shared/model/dependent-info-ess.model';

type EntityResponseType = HttpResponse<IDependentInfoEss>;
type EntityArrayResponseType = HttpResponse<IDependentInfoEss[]>;

@Injectable({ providedIn: 'root' })
export class DependentInfoEssService {
    public resourceUrl = SERVER_API_URL + 'api/dependent-infos';

    constructor(protected http: HttpClient) {}

    create(dependentInfo: IDependentInfoEss): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dependentInfo);
        return this.http
            .post<IDependentInfoEss>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(dependentInfo: IDependentInfoEss): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dependentInfo);
        return this.http
            .put<IDependentInfoEss>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDependentInfoEss>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDependentInfoEss[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(dependentInfo: IDependentInfoEss): IDependentInfoEss {
        const copy: IDependentInfoEss = Object.assign({}, dependentInfo, {
            marriageDate:
                dependentInfo.marriageDate != null && dependentInfo.marriageDate.isValid()
                    ? dependentInfo.marriageDate.format(DATE_FORMAT)
                    : null,
            hireDate: dependentInfo.hireDate != null && dependentInfo.hireDate.isValid() ? dependentInfo.hireDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.marriageDate = res.body.marriageDate != null ? moment(res.body.marriageDate) : null;
            res.body.hireDate = res.body.hireDate != null ? moment(res.body.hireDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((dependentInfo: IDependentInfoEss) => {
                dependentInfo.marriageDate = dependentInfo.marriageDate != null ? moment(dependentInfo.marriageDate) : null;
                dependentInfo.hireDate = dependentInfo.hireDate != null ? moment(dependentInfo.hireDate) : null;
            });
        }
        return res;
    }
}
