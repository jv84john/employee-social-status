import { Moment } from 'moment';

export interface IPersonalDataEss {
    id?: number;
    fullName?: string;
    oracleNumber?: string;
    jobFunction?: string;
    appointmentDate?: Moment;
    schoolAdministration?: string;
    gender?: string;
    sector?: string;
    socialStatus?: string;
    nationality?: string;
    contactNumber?: string;
    createdAt?: Moment;
    createdBy?: string;
    modifiedAt?: Moment;
    modifiedBy?: string;
    attachmentInfoId?: number;
}

export class PersonalDataEss implements IPersonalDataEss {
    constructor(
        public id?: number,
        public fullName?: string,
        public oracleNumber?: string,
        public jobFunction?: string,
        public appointmentDate?: Moment,
        public schoolAdministration?: string,
        public gender?: string,
        public sector?: string,
        public socialStatus?: string,
        public nationality?: string,
        public contactNumber?: string,
        public createdAt?: Moment,
        public createdBy?: string,
        public modifiedAt?: Moment,
        public modifiedBy?: string,
        public attachmentInfoId?: number
    ) {}
}
