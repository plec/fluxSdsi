export interface IRefDomaine {
    id?: number;
    code?: string;
    libelle?: string;
}

export class RefDomaine implements IRefDomaine {
    constructor(public id?: number, public code?: string, public libelle?: string) {}
}
