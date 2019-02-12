import { IRefZone } from 'app/shared/model/ref-zone.model';

export interface IRefFonction {
    id?: number;
    code?: string;
    codeZone?: string;
    codeFonction?: string;
    libelle?: string;
    refTypeFonctionId?: number;
    codeZones?: IRefZone[];
}

export class RefFonction implements IRefFonction {
    constructor(
        public id?: number,
        public code?: string,
        public codeZone?: string,
        public codeFonction?: string,
        public libelle?: string,
        public refTypeFonctionId?: number,
        public codeZones?: IRefZone[]
    ) {}
}
