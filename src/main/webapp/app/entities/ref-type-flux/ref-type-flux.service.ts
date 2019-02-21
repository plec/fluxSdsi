import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefTypeFlux } from 'app/shared/model/ref-type-flux.model';

type EntityResponseType = HttpResponse<IRefTypeFlux>;
type EntityArrayResponseType = HttpResponse<IRefTypeFlux[]>;

@Injectable({ providedIn: 'root' })
export class RefTypeFluxService {
    public resourceUrl = SERVER_API_URL + 'api/ref-type-fluxes';

    constructor(protected http: HttpClient) {}

    create(refTypeFlux: IRefTypeFlux): Observable<EntityResponseType> {
        return this.http.post<IRefTypeFlux>(this.resourceUrl, refTypeFlux, { observe: 'response' });
    }

    update(refTypeFlux: IRefTypeFlux): Observable<EntityResponseType> {
        return this.http.put<IRefTypeFlux>(this.resourceUrl, refTypeFlux, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefTypeFlux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefTypeFlux[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
