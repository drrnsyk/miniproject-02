import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CancelComponent } from './components/cancel/cancel.component';
import { AccounddelComponent } from './components/dashboard/accounddel.component';
import { AccountComponent } from './components/dashboard/account.component';
import { AccountaddComponent } from './components/dashboard/accountadd.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailComponent } from './components/detail/detail.component';
import { ErrorComponent } from './components/error/error.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SearchComponent } from './components/search/search.component';
import { SignupComponent } from './components/signup/signup.component';
import { StoreComponent } from './components/store/store.component';
import { SubscribeComponent } from './components/subscribe/subscribe.component';
import { SuccessComponent } from './components/success/success.component';
import { AuthGuard } from './service/auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'sub/subscribe', component: SubscribeComponent},
  { path: 'sub/success', component: SuccessComponent},
  { path: 'sub/cancel', component: CancelComponent},
  { path: 'auth/login', component: LoginComponent},
  { path: 'auth/signup', component: SignupComponent},
  { path: 'api/dashboard', component: DashboardComponent, canActivate:[AuthGuard], data: { isAdmin: true }},
  { path: 'api/dashboard/account/admin/add', component: AccountaddComponent, canActivate:[AuthGuard], data: { isAdmin: true }},
  { path: 'api/dashboard/account/:id', component: AccountComponent, canActivate:[AuthGuard], data: { isAdmin: true }},
  { path: 'api/dashboard/account/:id/delete', component: AccounddelComponent, canActivate:[AuthGuard], data: { isAdmin: true }},
  { path: 'api/search', component: SearchComponent, canActivate:[AuthGuard]},
  { path: 'api/stores', component: StoreComponent, canActivate:[AuthGuard]},
  { path: 'api/deal/detail/:dealID', component: DetailComponent, canActivate:[AuthGuard]},
  { path: 'api/error', component: ErrorComponent, canActivate:[AuthGuard]},
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
