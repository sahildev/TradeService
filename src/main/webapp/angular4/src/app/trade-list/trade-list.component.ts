import { Component, OnInit, ViewChild } from '@angular/core';
import { Trade } from '../trade';
import { TradeService } from '../trade.service';

import { MatTableDataSource } from '@angular/material';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'app-trade-list',
  templateUrl: './trade-list.component.html',
  styleUrls: ['./trade-list.component.css']
})

export class TradeListComponent implements OnInit {

  dataSource = new UserDataSource(this.tradeService);
  displayedColumnsTrade = ['tradeId', 'tradeName', 'quantity', 'commodity', 'location', 'counterparty'];


  constructor(private tradeService: TradeService) { }

  ngOnInit() {
  }

}

export class UserDataSource extends DataSource<any> {
  constructor(private tradeService: TradeService) {
    super();
  }
  connect(): Observable<Trade[]> {
    return this.tradeService.getAll();
  }
  disconnect() { }
}

