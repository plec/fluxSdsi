export interface IFlux {
    id?: number;
    environnement?: string;
    type?: string;
    sourceIP?: string;
    sourcePort?: string;
    sourceZone?: string;
    destIP?: string;
    destPort?: string;
    destZone?: string;
    environnementId?: number;
    sourceZoneId?: number;
    destZoneId?: number;
    typeId?: number;
}

export class Flux implements IFlux {
    constructor(
        public id?: number,
        public environnement?: string,
        public type?: string,
        public sourceIP?: string,
        public sourcePort?: string,
        public sourceZone?: string,
        public destIP?: string,
        public destPort?: string,
        public destZone?: string,
        public environnementId?: number,
        public sourceZoneId?: number,
        public destZoneId?: number,
        public typeId?: number
    ) {}
}
