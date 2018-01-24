import { Component, OnInit, ChangeDetectorRef  } from '@angular/core';

//imports to handle api requests
import {Http, Response} from "@angular/http";
//import {Observable} from "rxjs/Rx";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

//Imports to handle trade Creation
import { FormControl, FormGroup } from '@angular/forms';
import { Trade } from './trade';
import { TradeService } from './trade.service';

//Imports to handle market data 
import { MatTableDataSource } from '@angular/material';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import { marketData } from './marketData';
import {marketDataService} from './marketData.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {
  
  public submitted:boolean;
  tradeCreation : FormGroup;
  trade: Trade;
  statusCode: number;
  dataSource2: any;
  displayedColumns: any;
  interval: any;

  constructor(private tradeService: TradeService,
  private marketDataService: marketDataService, private changeDetectorRefs: ChangeDetectorRef) {}

  refresh() {
  this.dataSource2 = new MarketDataSource(this.marketDataService);
  this.displayedColumns = ['metalId', 'metalIdentifier','metalName','metalRate'];
  this.changeDetectorRefs.detectChanges();
  
  }

  ngOnInit() {
        this.tradeCreation = new FormGroup({
            tradeId: new FormControl(''),
            tradeName: new FormControl(''),
            quantity: new FormControl(''),
            commodity: new FormControl(''),
            location: new FormControl(''),
            counterparty: new FormControl('')
        });
        //   this.interval = setInterval(() => { 
            this.refresh();
        // }, 1000);
        
    }

    onSubmit({value,valid}: {value:TradeCreation, valid:boolean}) {
      //console.log(value);
      this.tradeService.create(value)
      .subscribe(successCode => {
		              this.statusCode = successCode;
			},
		        errorCode => this.statusCode = errorCode);

    }
}

export interface TradeCreation {
  tradeId:string;
  tradeName:string;
  quantity:number;
  commodity:string;
  location:string;
  counterparty:string;
}


/**************   Market Data Service Section start       */

export class MarketDataSource extends DataSource<any> {
  constructor(private marketDataService: marketDataService) {
    super();
  }
  connect(): Observable<marketData[]> {
    return this.marketDataService.getAll();
  }
  disconnect() { }
}