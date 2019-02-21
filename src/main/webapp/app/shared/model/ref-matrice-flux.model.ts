export interface IRefMatriceFlux {
    id?: number;
    environnement?: string;
    typeFlux?: string;
    sourceVlan?: string;
    port?: string;
    destVlan?: string;
    typeAuthorisation?: string;
    destVlanId?: number;
    sourceVlanId?: number;
    typeAuthorisationId?: number;
}

export class RefMatriceFlux implements IRefMatriceFlux {
    constructor(
        public id?: number,
        public environnement?: string,
        public typeFlux?: string,
        public sourceVlan?: string,
        public port?: string,
        public destVlan?: string,
        public typeAuthorisation?: string,
        public destVlanId?: number,
        public sourceVlanId?: number,
        public typeAuthorisationId?: number
    ) {}
}
