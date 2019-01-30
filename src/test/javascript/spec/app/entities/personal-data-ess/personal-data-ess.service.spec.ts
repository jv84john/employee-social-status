/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PersonalDataEssService } from 'app/entities/personal-data-ess/personal-data-ess.service';
import { IPersonalDataEss, PersonalDataEss } from 'app/shared/model/personal-data-ess.model';

describe('Service Tests', () => {
    describe('PersonalDataEss Service', () => {
        let injector: TestBed;
        let service: PersonalDataEssService;
        let httpMock: HttpTestingController;
        let elemDefault: IPersonalDataEss;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PersonalDataEssService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new PersonalDataEss(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                currentDate,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        appointmentDate: currentDate.format(DATE_FORMAT),
                        createdAt: currentDate.format(DATE_TIME_FORMAT),
                        modifiedAt: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a PersonalDataEss', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        appointmentDate: currentDate.format(DATE_FORMAT),
                        createdAt: currentDate.format(DATE_TIME_FORMAT),
                        modifiedAt: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        appointmentDate: currentDate,
                        createdAt: currentDate,
                        modifiedAt: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new PersonalDataEss(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a PersonalDataEss', async () => {
                const returnedFromService = Object.assign(
                    {
                        fullName: 'BBBBBB',
                        oracleNumber: 'BBBBBB',
                        jobFunction: 'BBBBBB',
                        appointmentDate: currentDate.format(DATE_FORMAT),
                        schoolAdministration: 'BBBBBB',
                        gender: 'BBBBBB',
                        sector: 'BBBBBB',
                        socialStatus: 'BBBBBB',
                        nationality: 'BBBBBB',
                        contactNumber: 'BBBBBB',
                        createdAt: currentDate.format(DATE_TIME_FORMAT),
                        createdBy: 'BBBBBB',
                        modifiedAt: currentDate.format(DATE_TIME_FORMAT),
                        modifiedBy: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        appointmentDate: currentDate,
                        createdAt: currentDate,
                        modifiedAt: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of PersonalDataEss', async () => {
                const returnedFromService = Object.assign(
                    {
                        fullName: 'BBBBBB',
                        oracleNumber: 'BBBBBB',
                        jobFunction: 'BBBBBB',
                        appointmentDate: currentDate.format(DATE_FORMAT),
                        schoolAdministration: 'BBBBBB',
                        gender: 'BBBBBB',
                        sector: 'BBBBBB',
                        socialStatus: 'BBBBBB',
                        nationality: 'BBBBBB',
                        contactNumber: 'BBBBBB',
                        createdAt: currentDate.format(DATE_TIME_FORMAT),
                        createdBy: 'BBBBBB',
                        modifiedAt: currentDate.format(DATE_TIME_FORMAT),
                        modifiedBy: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        appointmentDate: currentDate,
                        createdAt: currentDate,
                        modifiedAt: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a PersonalDataEss', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
