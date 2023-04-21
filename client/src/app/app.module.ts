import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SearchComponent } from './components/search/search.component';
import { HomeComponent } from './components/home/home.component';
import { InterceptorService } from './service/interceptor.service';
import { AccountComponent } from './components/dashboard/account.component';
import { AccounddelComponent } from './components/dashboard/accounddel.component';
import { DetailComponent } from './components/detail/detail.component';
import { StoreComponent } from './components/store/store.component';
import { AccountaddComponent } from './components/dashboard/accountadd.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    DashboardComponent,
    SearchComponent,
    HomeComponent,
    AccountComponent,
    AccounddelComponent,
    DetailComponent,
    StoreComponent,
    AccountaddComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{
    provide:HTTP_INTERCEPTORS, 
    useClass:InterceptorService,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
