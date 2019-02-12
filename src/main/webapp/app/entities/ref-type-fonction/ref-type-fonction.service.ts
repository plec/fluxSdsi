import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefTypeFonction } from 'app/shared/model/ref-type-fonction.model';

type EntityResponseType = HttpResponse<IRefTypeFonction>;
type EntityArrayResponseType = HttpResponse<IRefTypeFonction[]>;

@Injectable({ providedIn: 'root' })
export class RefTypeFonctionService {
    public resourceUrl = SERVER_API_URL + 'api/ref-type-fonctions';

    constructor(protected http: HttpClient) {}

    create(refTypeFonction: IRefTypeFonction): Observable<EntityResponseType> {
        return this.http.post<IRefTypeFonction>(this.resourceUrl, refTypeFonction, { observe: 'response' });
    }

    update(refTypeFonction: IRefTypeFonction): Observable<EntityResponseType> {
        return this.http.put<IRefTypeFonction>(this.resourceUrl, refTypeFonction, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefTypeFonction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefTypeFonction[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
