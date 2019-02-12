import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefFlux } from 'app/shared/model/ref-flux.model';

type EntityResponseType = HttpResponse<IRefFlux>;
type EntityArrayResponseType = HttpResponse<IRefFlux[]>;

@Injectable({ providedIn: 'root' })
export class RefFluxService {
    public resourceUrl = SERVER_API_URL + 'api/ref-fluxes';

    constructor(protected http: HttpClient) {}

    create(refFlux: IRefFlux): Observable<EntityResponseType> {
        return this.http.post<IRefFlux>(this.resourceUrl, refFlux, { observe: 'response' });
    }

    update(refFlux: IRefFlux): Observable<EntityResponseType> {
        return this.http.put<IRefFlux>(this.resourceUrl, refFlux, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefFlux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefFlux[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
