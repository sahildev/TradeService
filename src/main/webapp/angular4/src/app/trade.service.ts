import { Injectable } from '@angular/core';
import { Trade } from './trade';

//Imports to cater to consumption of API
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class TradeService {

    private baseUrl: string = 'http://localhost:8080';

    constructor(private http: Http) {
    }

    getAll(): Observable<Trade[]> {
        let trad$ = this.http
            .get(`${this.baseUrl}/v1/trade/`)
            .map(mapTrades);
        return trad$;
    }

    get(tradeId: string): Observable<Trade> {
        let trad$ = this.http
            .get(`${this.baseUrl}/v1/trade/${tradeId}`)
            .map(mapTrade);
        return trad$;

    }

    save(trade: Trade): Observable<number> {
        console.log("Inside Update");
        return this
            .http
            .put(`${this.baseUrl}/v1/trade/${trade.tradeId}`, trade)
            .map(success => success.status)
            .catch(this.handleError);
    }

    //Create trade
    create(trade: Trade): Observable<number> {
        console.log("Inside create");
        return this.http.post(`${this.baseUrl}/v1/trade/`, trade)
            .map(success => success.status)
            .catch(this.handleError);
    }

    private handleError(error: Response | any) {
        console.error(error.message || error);
        return Observable.throw(error.status);
    }
}

function mapTrades(response: Response): Trade[] {
    return response.json().map(toTrade);
}

function mapTrade(response: Response): Trade {
    return toTrade(response.json());
}

function toTrade(tr: any): Trade {
    let trade = <Trade>({
        tradeId: tr.tradeId,
        tradeName: tr.tradeName,
        quantity: Number.parseInt(tr.quantity),
        commodity: tr.commodity,
        location: tr.location,
        counterparty: tr.counterparty,
    });
    console.log('Parsed trade:', trade);
    return trade;
}
