import { IFlux } from 'app/shared/model/flux.model';

export const enum TypeFlux {
    TCP = 'TCP',
    UDP = 'UDP',
    TCP_UDP = 'TCP_UDP'
}

export interface IRefFlux {
    id?: number;
    code?: string;
    type?: TypeFlux;
    libelle?: string;
    codes?: IFlux[];
}

export class RefFlux implements IRefFlux {
    constructor(public id?: number, public code?: string, public type?: TypeFlux, public libelle?: string, public codes?: IFlux[]) {}
}
