import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TradeListComponent } from "./trade-list/trade-list.component";
import { TradeDetailsComponent } from "./trade-details/trade-details.component";

// Route config let's you map routes to components
const routes: Routes = [
  // map '/trades' to the people list component
  {
    path: 'trades',
    component: TradeListComponent,
  },
  // map 'trades/:tradeId' to person details component
  {
    path: 'trades/:tradeId', 
    component: TradeDetailsComponent 
  },
  // map '/' to '/trades' as our default route
  {
    path: '',
    redirectTo: '/trades',
    pathMatch: 'full'
  },
];

// HERE: New module
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
