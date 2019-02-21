import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefVlan } from 'app/shared/model/ref-vlan.model';

type EntityResponseType = HttpResponse<IRefVlan>;
type EntityArrayResponseType = HttpResponse<IRefVlan[]>;

@Injectable({ providedIn: 'root' })
export class RefVlanService {
    public resourceUrl = SERVER_API_URL + 'api/ref-vlans';

    constructor(protected http: HttpClient) {}

    create(refVlan: IRefVlan): Observable<EntityResponseType> {
        return this.http.post<IRefVlan>(this.resourceUrl, refVlan, { observe: 'response' });
    }

    update(refVlan: IRefVlan): Observable<EntityResponseType> {
        return this.http.put<IRefVlan>(this.resourceUrl, refVlan, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefVlan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefVlan[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
