import { IFlux } from 'app/shared/model/flux.model';

export interface IRefEnvironnement {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IFlux[];
}

export class RefEnvironnement implements IRefEnvironnement {
    constructor(public id?: number, public code?: string, public libelle?: string, public codes?: IFlux[]) {}
}
