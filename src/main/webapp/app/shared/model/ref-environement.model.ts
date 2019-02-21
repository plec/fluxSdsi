import { IDemandeFlux } from 'app/shared/model/demande-flux.model';

export interface IRefEnvironement {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IDemandeFlux[];
}

export class RefEnvironement implements IRefEnvironement {
    constructor(public id?: number, public code?: string, public libelle?: string, public codes?: IDemandeFlux[]) {}
}
