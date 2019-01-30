export interface IAttachmentInfoEss {
    id?: number;
    marriageCert?: string;
    salaryCert?: string;
    deathCert?: string;
    divorceDoc?: string;
    synopsis?: string;
}

export class AttachmentInfoEss implements IAttachmentInfoEss {
    constructor(
        public id?: number,
        public marriageCert?: string,
        public salaryCert?: string,
        public deathCert?: string,
        public divorceDoc?: string,
        public synopsis?: string
    ) {}
}
