import { IFlux } from 'app/shared/model/flux.model';

export interface IRefZone {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IFlux[];
    codes?: IFlux[];
    codeId?: number;
}

export class RefZone implements IRefZone {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public codes?: IFlux[],
        public codes?: IFlux[],
        public codeId?: number
    ) {}
}
