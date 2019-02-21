import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDemandeFlux } from 'app/shared/model/demande-flux.model';

type EntityResponseType = HttpResponse<IDemandeFlux>;
type EntityArrayResponseType = HttpResponse<IDemandeFlux[]>;

@Injectable({ providedIn: 'root' })
export class DemandeFluxService {
    public resourceUrl = SERVER_API_URL + 'api/demande-fluxes';

    constructor(protected http: HttpClient) {}

    create(demandeFlux: IDemandeFlux): Observable<EntityResponseType> {
        return this.http.post<IDemandeFlux>(this.resourceUrl, demandeFlux, { observe: 'response' });
    }

    update(demandeFlux: IDemandeFlux): Observable<EntityResponseType> {
        return this.http.put<IDemandeFlux>(this.resourceUrl, demandeFlux, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDemandeFlux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDemandeFlux[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
