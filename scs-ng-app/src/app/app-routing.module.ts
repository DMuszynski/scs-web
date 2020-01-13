import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { UserComponent } from "./user/user.component";
import { RankingComponent } from "./ranking/ranking.component";
import { ShopComponent } from "./shop/shop.component";
import { AuthGuardService } from "./services/auth-guard.service";
import {FinalizePaymentComponent} from "./finalize-payment/finalize-payment.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserComponent, canActivate: [AuthGuardService] },
  { path: 'ranking', component: RankingComponent },
  { path: 'user/shop', component: ShopComponent, canActivate: [AuthGuardService] },
  { path: 'finalizePayment', component: FinalizePaymentComponent, canActivate: [AuthGuardService]},
  { path: '**', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
