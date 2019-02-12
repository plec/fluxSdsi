import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefNumero } from 'app/shared/model/ref-numero.model';

type EntityResponseType = HttpResponse<IRefNumero>;
type EntityArrayResponseType = HttpResponse<IRefNumero[]>;

@Injectable({ providedIn: 'root' })
export class RefNumeroService {
    public resourceUrl = SERVER_API_URL + 'api/ref-numeros';

    constructor(protected http: HttpClient) {}

    create(refNumero: IRefNumero): Observable<EntityResponseType> {
        return this.http.post<IRefNumero>(this.resourceUrl, refNumero, { observe: 'response' });
    }

    update(refNumero: IRefNumero): Observable<EntityResponseType> {
        return this.http.put<IRefNumero>(this.resourceUrl, refNumero, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefNumero>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefNumero[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
