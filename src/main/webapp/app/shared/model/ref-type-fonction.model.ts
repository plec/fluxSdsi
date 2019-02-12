import { IRefFonction } from 'app/shared/model/ref-fonction.model';

export interface IRefTypeFonction {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IRefFonction[];
}

export class RefTypeFonction implements IRefTypeFonction {
    constructor(public id?: number, public code?: string, public libelle?: string, public codes?: IRefFonction[]) {}
}
