import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

export interface IRefTypeAuthorisation {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IRefMatriceFlux[];
}

export class RefTypeAuthorisation implements IRefTypeAuthorisation {
    constructor(public id?: number, public code?: string, public libelle?: string, public codes?: IRefMatriceFlux[]) {}
}
