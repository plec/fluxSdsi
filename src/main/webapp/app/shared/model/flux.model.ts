import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';
import { IRefFlux } from 'app/shared/model/ref-flux.model';
import { IRefZone } from 'app/shared/model/ref-zone.model';

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
    refEnvironnement?: IRefEnvironnement;
    refFlux?: IRefFlux;
    refZone?: IRefZone;
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
        public refEnvironnement?: IRefEnvironnement,
        public refFlux?: IRefFlux,
        public refZone?: IRefZone
    ) {}
}
