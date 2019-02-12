import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRefFonction } from 'app/shared/model/ref-fonction.model';

type EntityResponseType = HttpResponse<IRefFonction>;
type EntityArrayResponseType = HttpResponse<IRefFonction[]>;

@Injectable({ providedIn: 'root' })
export class RefFonctionService {
    public resourceUrl = SERVER_API_URL + 'api/ref-fonctions';

    constructor(protected http: HttpClient) {}

    create(refFonction: IRefFonction): Observable<EntityResponseType> {
        return this.http.post<IRefFonction>(this.resourceUrl, refFonction, { observe: 'response' });
    }

    update(refFonction: IRefFonction): Observable<EntityResponseType> {
        return this.http.put<IRefFonction>(this.resourceUrl, refFonction, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRefFonction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRefFonction[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
