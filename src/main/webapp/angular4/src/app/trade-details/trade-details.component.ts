import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Trade } from '../trade';
import { TradeService } from '../trade.service';

@Component({
  selector: 'app-trade-details',
  templateUrl: './trade-details.component.html',
  styleUrls: ['./trade-details.component.css']
})
export class TradeDetailsComponent implements OnInit {

  trade: Trade;
  sub: any;
  statusCode: number;

  saveTradeDetails(){
       // alert(`saved!!! ${JSON.stringify(this.trade)}`);
        this.tradeService.save(this.trade)
        .subscribe(successCode => {
		              this.statusCode = successCode;
			},
		        errorCode => this.statusCode = errorCode);
    }

  constructor(private tradeService: TradeService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      let tradeId = params['tradeId'];
      console.log('getting Trade with id: ', tradeId);
      this.tradeService
        .get(tradeId)
        .subscribe(tr => this.trade = tr);
    });
   
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoTradesList() {
    let link = ['/trades'];
    this.router.navigate(link);
  }


}