import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefMatriceFlux } from 'app/shared/model/ref-matrice-flux.model';

type EntityResponseType = HttpResponse<IRefMatriceFlux>;
type EntityArrayResponseType = HttpResponse<IRefMatriceFlux[]>;

@Injectable({ providedIn: 'root' })
export class RefMatriceFluxService {
    public resourceUrl = SERVER_API_URL + 'api/ref-matrice-fluxes';

    constructor(protected http: HttpClient) {}

    create(refMatriceFlux: IRefMatriceFlux): Observable<EntityResponseType> {
        return this.http.post<IRefMatriceFlux>(this.resourceUrl, refMatriceFlux, { observe: 'response' });
    }

    update(refMatriceFlux: IRefMatriceFlux): Observable<EntityResponseType> {
        return this.http.put<IRefMatriceFlux>(this.resourceUrl, refMatriceFlux, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefMatriceFlux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefMatriceFlux[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
