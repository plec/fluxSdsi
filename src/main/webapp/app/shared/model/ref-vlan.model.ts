import { IDemandeFlux } from 'app/shared/model/demande-flux.model';
import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

export interface IRefVlan {
    id?: number;
    code?: string;
    codeZone?: string;
    libelle?: string;
    codes?: IDemandeFlux[];
    codes?: IDemandeFlux[];
    codes?: IRefMatriceFlux[];
    codes?: IRefMatriceFlux[];
    codeZoneId?: number;
}

export class RefVlan implements IRefVlan {
    constructor(
        public id?: number,
        public code?: string,
        public codeZone?: string,
        public libelle?: string,
        public codes?: IDemandeFlux[],
        public codes?: IDemandeFlux[],
        public codes?: IRefMatriceFlux[],
        public codes?: IRefMatriceFlux[],
        public codeZoneId?: number
    ) {}
}
