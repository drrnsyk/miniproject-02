import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccounddelComponent } from './components/dashboard/accounddel.component';
import { AccountComponent } from './components/dashboard/account.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SearchComponent } from './components/search/search.component';
import { SignupComponent } from './components/signup/signup.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'auth/login', component: LoginComponent},
  { path: 'auth/signup', component: SignupComponent},
  { path: 'api/home', component: HomeComponent},
  { path: 'api/dashboard', component: DashboardComponent},
  { path: 'api/dashboard/account/:id', component: AccountComponent},
  { path: 'api/dashboard/account/:id/delete', component: AccounddelComponent},
  { path: 'api/search', component: SearchComponent},
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
