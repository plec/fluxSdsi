import { IRefVlan } from 'app/shared/model/ref-vlan.model';

export interface IRefZone {
    id?: number;
    code?: string;
    libelle?: string;
    codes?: IRefVlan[];
}

export class RefZone implements IRefZone {
    constructor(public id?: number, public code?: string, public libelle?: string, public codes?: IRefVlan[]) {}
}
