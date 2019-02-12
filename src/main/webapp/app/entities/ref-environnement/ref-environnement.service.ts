import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefEnvironnement } from 'app/shared/model/ref-environnement.model';

type EntityResponseType = HttpResponse<IRefEnvironnement>;
type EntityArrayResponseType = HttpResponse<IRefEnvironnement[]>;

@Injectable({ providedIn: 'root' })
export class RefEnvironnementService {
    public resourceUrl = SERVER_API_URL + 'api/ref-environnements';

    constructor(protected http: HttpClient) {}

    create(refEnvironnement: IRefEnvironnement): Observable<EntityResponseType> {
        return this.http.post<IRefEnvironnement>(this.resourceUrl, refEnvironnement, { observe: 'response' });
    }

    update(refEnvironnement: IRefEnvironnement): Observable<EntityResponseType> {
        return this.http.put<IRefEnvironnement>(this.resourceUrl, refEnvironnement, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefEnvironnement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefEnvironnement[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
