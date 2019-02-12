import { IRefFonction } from 'app/shared/model/ref-fonction.model';
import { IFlux } from 'app/shared/model/flux.model';

export interface IRefZone {
    id?: number;
    code?: string;
    libelle?: string;
    refFonction?: IRefFonction;
    codes?: IFlux[];
}

export class RefZone implements IRefZone {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public refFonction?: IRefFonction,
        public codes?: IFlux[]
    ) {}
}
