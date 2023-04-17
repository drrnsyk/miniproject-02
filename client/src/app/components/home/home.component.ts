import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn!: boolean;

  constructor(private authSvc: AuthService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authSvc.isLoggedIn()
  }

}
