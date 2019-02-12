import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefZone } from 'app/shared/model/ref-zone.model';

type EntityResponseType = HttpResponse<IRefZone>;
type EntityArrayResponseType = HttpResponse<IRefZone[]>;

@Injectable({ providedIn: 'root' })
export class RefZoneService {
    public resourceUrl = SERVER_API_URL + 'api/ref-zones';

    constructor(protected http: HttpClient) {}

    create(refZone: IRefZone): Observable<EntityResponseType> {
        return this.http.post<IRefZone>(this.resourceUrl, refZone, { observe: 'response' });
    }

    update(refZone: IRefZone): Observable<EntityResponseType> {
        return this.http.put<IRefZone>(this.resourceUrl, refZone, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefZone>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefZone[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
