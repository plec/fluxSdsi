import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefSite } from 'app/shared/model/ref-site.model';

type EntityResponseType = HttpResponse<IRefSite>;
type EntityArrayResponseType = HttpResponse<IRefSite[]>;

@Injectable({ providedIn: 'root' })
export class RefSiteService {
    public resourceUrl = SERVER_API_URL + 'api/ref-sites';

    constructor(protected http: HttpClient) {}

    create(refSite: IRefSite): Observable<EntityResponseType> {
        return this.http.post<IRefSite>(this.resourceUrl, refSite, { observe: 'response' });
    }

    update(refSite: IRefSite): Observable<EntityResponseType> {
        return this.http.put<IRefSite>(this.resourceUrl, refSite, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefSite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefSite[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
