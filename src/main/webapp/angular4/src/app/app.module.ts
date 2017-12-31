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
import { TradeDetailsComponent } from './trade-details/trade-details.component';

@NgModule({
  declarations: [
    AppComponent,
    TradeListComponent,
    TradeDetailsComponent
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
  providers: [TradeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
