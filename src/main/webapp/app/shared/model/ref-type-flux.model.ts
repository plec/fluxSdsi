import { IDemandeFlux } from 'app/shared/model/demande-flux.model';

export interface IRefTypeFlux {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IDemandeFlux[];
}

export class RefTypeFlux implements IRefTypeFlux {
    constructor(public id?: number, public code?: string, public libelle?: string, public codes?: IDemandeFlux[]) {}
}
