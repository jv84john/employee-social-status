import { Moment } from 'moment';

export interface IDependentInfoEss {
    id?: number;
    name?: string;
    marriageDate?: Moment;
    workingStatus?: string;
    employer?: string;
    employerType?: string;
    hireDate?: Moment;
    houseAllowanceProvided?: string;
    governmentHousing?: string;
    personalDataId?: number;
}

export class DependentInfoEss implements IDependentInfoEss {
    constructor(
        public id?: number,
        public name?: string,
        public marriageDate?: Moment,
        public workingStatus?: string,
        public employer?: string,
        public employerType?: string,
        public hireDate?: Moment,
        public houseAllowanceProvided?: string,
        public governmentHousing?: string,
        public personalDataId?: number
    ) {}
}
