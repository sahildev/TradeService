import 'hammerjs';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from "./app-routing.module";
import { AppMaterialModule } from './app.material.module';
import { AppComponent } from './app.component';

//Trade service specific imports
import { TradeListComponent } from './trade-list/trade-list.component';
import { TradeService } from './trade.service';
import { marketDataService } from './marketData.service';
import { ReferenceDataService } from './referenceData.service';
import { TradeDetailsComponent } from './trade-details/trade-details.component';
import { ReferenceDataComponent } from "./reference-data/reference-data.component";

@NgModule({
  declarations: [
    AppComponent,
    TradeListComponent,
    TradeDetailsComponent,
    ReferenceDataComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    AppMaterialModule,
    BrowserAnimationsModule
  ],
  providers: [TradeService,marketDataService,ReferenceDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
