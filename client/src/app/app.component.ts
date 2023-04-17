import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isLoggedIn!: boolean;
  role = ""

  constructor(private authSvc: AuthService) {}

  checkIfLoggedIn() {
    this.isLoggedIn = this.authSvc.isLoggedIn()
    console.info('>>> AppComponent: checkIfLoggedIn(): ', this.isLoggedIn)
    this.role = this.authSvc.getRole()
    console.info('>>> AppComponent: checkIfLoggedIn(): ', this.role)
  }

  logout() {
    this.authSvc.logout()
  }

}
