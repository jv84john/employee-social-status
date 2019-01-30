/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { DependentInfoEssService } from 'app/entities/dependent-info-ess/dependent-info-ess.service';
import { IDependentInfoEss, DependentInfoEss } from 'app/shared/model/dependent-info-ess.model';

describe('Service Tests', () => {
    describe('DependentInfoEss Service', () => {
        let injector: TestBed;
        let service: DependentInfoEssService;
        let httpMock: HttpTestingController;
        let elemDefault: IDependentInfoEss;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DependentInfoEssService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new DependentInfoEss(
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        marriageDate: currentDate.format(DATE_FORMAT),
                        hireDate: currentDate.format(DATE_FORMAT)
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

            it('should create a DependentInfoEss', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        marriageDate: currentDate.format(DATE_FORMAT),
                        hireDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        marriageDate: currentDate,
                        hireDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new DependentInfoEss(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DependentInfoEss', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        marriageDate: currentDate.format(DATE_FORMAT),
                        workingStatus: 'BBBBBB',
                        employer: 'BBBBBB',
                        employerType: 'BBBBBB',
                        hireDate: currentDate.format(DATE_FORMAT),
                        houseAllowanceProvided: 'BBBBBB',
                        governmentHousing: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        marriageDate: currentDate,
                        hireDate: currentDate
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

            it('should return a list of DependentInfoEss', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        marriageDate: currentDate.format(DATE_FORMAT),
                        workingStatus: 'BBBBBB',
                        employer: 'BBBBBB',
                        employerType: 'BBBBBB',
                        hireDate: currentDate.format(DATE_FORMAT),
                        houseAllowanceProvided: 'BBBBBB',
                        governmentHousing: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        marriageDate: currentDate,
                        hireDate: currentDate
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

            it('should delete a DependentInfoEss', async () => {
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
