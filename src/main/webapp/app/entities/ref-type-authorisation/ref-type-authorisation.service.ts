import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefTypeAuthorisation } from 'app/shared/model/ref-type-authorisation.model';

type EntityResponseType = HttpResponse<IRefTypeAuthorisation>;
type EntityArrayResponseType = HttpResponse<IRefTypeAuthorisation[]>;

@Injectable({ providedIn: 'root' })
export class RefTypeAuthorisationService {
    public resourceUrl = SERVER_API_URL + 'api/ref-type-authorisations';

    constructor(protected http: HttpClient) {}

    create(refTypeAuthorisation: IRefTypeAuthorisation): Observable<EntityResponseType> {
        return this.http.post<IRefTypeAuthorisation>(this.resourceUrl, refTypeAuthorisation, { observe: 'response' });
    }

    update(refTypeAuthorisation: IRefTypeAuthorisation): Observable<EntityResponseType> {
        return this.http.put<IRefTypeAuthorisation>(this.resourceUrl, refTypeAuthorisation, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefTypeAuthorisation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefTypeAuthorisation[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
