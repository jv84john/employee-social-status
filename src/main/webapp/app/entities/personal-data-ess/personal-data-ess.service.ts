import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPersonalDataEss } from 'app/shared/model/personal-data-ess.model';

type EntityResponseType = HttpResponse<IPersonalDataEss>;
type EntityArrayResponseType = HttpResponse<IPersonalDataEss[]>;

@Injectable({ providedIn: 'root' })
export class PersonalDataEssService {
    public resourceUrl = SERVER_API_URL + 'api/personal-data';

    constructor(protected http: HttpClient) {}

    create(personalData: IPersonalDataEss): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(personalData);
        return this.http
            .post<IPersonalDataEss>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(personalData: IPersonalDataEss): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(personalData);
        return this.http
            .put<IPersonalDataEss>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPersonalDataEss>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPersonalDataEss[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(personalData: IPersonalDataEss): IPersonalDataEss {
        const copy: IPersonalDataEss = Object.assign({}, personalData, {
            appointmentDate:
                personalData.appointmentDate != null && personalData.appointmentDate.isValid()
                    ? personalData.appointmentDate.format(DATE_FORMAT)
                    : null,
            createdAt: personalData.createdAt != null && personalData.createdAt.isValid() ? personalData.createdAt.toJSON() : null,
            modifiedAt: personalData.modifiedAt != null && personalData.modifiedAt.isValid() ? personalData.modifiedAt.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.appointmentDate = res.body.appointmentDate != null ? moment(res.body.appointmentDate) : null;
            res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
            res.body.modifiedAt = res.body.modifiedAt != null ? moment(res.body.modifiedAt) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((personalData: IPersonalDataEss) => {
                personalData.appointmentDate = personalData.appointmentDate != null ? moment(personalData.appointmentDate) : null;
                personalData.createdAt = personalData.createdAt != null ? moment(personalData.createdAt) : null;
                personalData.modifiedAt = personalData.modifiedAt != null ? moment(personalData.modifiedAt) : null;
            });
        }
        return res;
    }
}
