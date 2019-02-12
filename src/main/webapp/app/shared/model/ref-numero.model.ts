export interface IRefNumero {
    id?: number;
    code?: string;
    libelle?: string;
}

export class RefNumero implements IRefNumero {
    constructor(public id?: number, public code?: string, public libelle?: string) {}
}
