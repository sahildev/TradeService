import { Injectable } from '@angular/core';
import { ReferenceData } from './ReferenceData';

//Imports to cater to consumption of API
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class ReferenceDataService {

    private baseUrl: string = 'http://localhost:8080';

    constructor(private http: Http) {
    }

    get(refCode: string): Observable<ReferenceData> {
        let refdata$ = this.http
            .get(`${this.baseUrl}/v1/refdata/${refCode}`)
            .map(mapReferenceData);
        return refdata$;

    }


}

function mapReferenceData(response: Response): ReferenceData {
    return toReferenceData(response.json());
}
function toReferenceData(rd: any): ReferenceData {
    let ReferenceData = <ReferenceData>({
        refCode: rd.refCode,
        refValue: rd.refValue,
        refDescription: rd.refDescription,
    });
    console.log('Parsed ReferenceData:', ReferenceData);
    return ReferenceData;
}