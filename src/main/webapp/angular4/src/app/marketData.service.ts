import { Injectable } from '@angular/core';
import { marketData } from './marketData';

//Imports to cater to consumption of API
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class marketDataService {

private baseUrl: string = 'http://localhost:8080';

    constructor(private http: Http) {
    }

    getAll(): Observable<marketData[]> {
        let metal$ = this.http
            .get(`${this.baseUrl}/v1/metal/`)
            .map(mapMarketData);
        return metal$;
    }
   
}

function mapMarketData(response: Response): marketData[] {
    return response.json().map(toMarketData);
}

function toMarketData(md: any): marketData {
    let marketData = <marketData>({
        metalId: md.metalId,
        metalIdentifier: md.metalIdentifier,
        metalName: md.metalName,
        metalRate: Number.parseInt(md.metalRate),
    });
    console.log('Parsed marketData:', marketData);
    return marketData;
}