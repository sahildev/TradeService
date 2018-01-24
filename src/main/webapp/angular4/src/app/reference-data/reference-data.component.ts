import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ReferenceData } from '../ReferenceData';
import { ReferenceDataService } from '../referenceData.service';



@Component({
  selector: 'app-trade-details',
  templateUrl: './reference-data.component.html',
  styleUrls: ['./reference-data.component.css']
})
export class ReferenceDataComponent implements OnInit {

  ReferenceData: ReferenceData;
  sub: any;

  constructor(private ReferenceDataService: ReferenceDataService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    console.log("refdata component loaded");
    this.sub = this.route.params.subscribe(params => {
      let refCode = params['refCode'];
      console.log('getting Reference data with id: ', refCode);
      this.ReferenceDataService
        .get(refCode)
        .subscribe(tr => this.ReferenceData = tr);

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