export interface IRefSite {
    id?: number;
    code?: string;
    libelle?: string;
}

export class RefSite implements IRefSite {
    constructor(public id?: number, public code?: string, public libelle?: string) {}
}
