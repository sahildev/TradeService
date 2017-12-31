import { Component } from '@angular/core';

//imports to handle api requests
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

//Imports to handle trade Creation
import { FormControl, FormGroup } from '@angular/forms';
import { Trade } from './trade';
import { TradeService } from './trade.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  public submitted:boolean;
  tradeCreation : FormGroup;
  trade: Trade;
  statusCode: number;

  constructor(private tradeService: TradeService) {
  }

  ngOnInit() {
        this.tradeCreation = new FormGroup({
            tradeId: new FormControl(''),
            tradeName: new FormControl(''),
            quantity: new FormControl('')
        });
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
}
