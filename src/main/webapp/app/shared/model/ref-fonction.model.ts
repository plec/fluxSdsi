export interface IRefFonction {
    id?: number;
    code?: string;
    libelle?: string;
    codeTypeFonctionId?: number;
}

export class RefFonction implements IRefFonction {
    constructor(public id?: number, public code?: string, public libelle?: string, public codeTypeFonctionId?: number) {}
}
