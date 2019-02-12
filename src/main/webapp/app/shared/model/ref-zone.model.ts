import { IFlux } from 'app/shared/model/flux.model';

export interface IRefZone {
    id?: number;
    code?: string;
    libelle?: string;
    refFonctionId?: number;
    codes?: IFlux[];
}

export class RefZone implements IRefZone {
    constructor(public id?: number, public code?: string, public libelle?: string, public refFonctionId?: number, public codes?: IFlux[]) {}
}
