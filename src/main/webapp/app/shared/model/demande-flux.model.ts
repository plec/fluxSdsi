export interface IDemandeFlux {
    id?: number;
    environnement?: string;
    typeFlux?: string;
    sourceIP?: string;
    sourcePort?: string;
    sourceVlan?: string;
    destIP?: string;
    destPort?: string;
    destVlan?: string;
    environnementId?: number;
    destVlanId?: number;
    sourceVlanId?: number;
    typeFluxId?: number;
}

export class DemandeFlux implements IDemandeFlux {
    constructor(
        public id?: number,
        public environnement?: string,
        public typeFlux?: string,
        public sourceIP?: string,
        public sourcePort?: string,
        public sourceVlan?: string,
        public destIP?: string,
        public destPort?: string,
        public destVlan?: string,
        public environnementId?: number,
        public destVlanId?: number,
        public sourceVlanId?: number,
        public typeFluxId?: number
    ) {}
}
