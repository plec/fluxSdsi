import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefDomaine } from 'app/shared/model/ref-domaine.model';

type EntityResponseType = HttpResponse<IRefDomaine>;
type EntityArrayResponseType = HttpResponse<IRefDomaine[]>;

@Injectable({ providedIn: 'root' })
export class RefDomaineService {
    public resourceUrl = SERVER_API_URL + 'api/ref-domaines';

    constructor(protected http: HttpClient) {}

    create(refDomaine: IRefDomaine): Observable<EntityResponseType> {
        return this.http.post<IRefDomaine>(this.resourceUrl, refDomaine, { observe: 'response' });
    }

    update(refDomaine: IRefDomaine): Observable<EntityResponseType> {
        return this.http.put<IRefDomaine>(this.resourceUrl, refDomaine, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefDomaine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefDomaine[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
