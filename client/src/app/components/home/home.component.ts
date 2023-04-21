import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { ProtectedService } from 'src/app/service/protected.service';
import { Deal } from '../model/model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn!: boolean
  deals: Deal[] = []

  constructor(private authSvc: AuthService, private protectedSvc: ProtectedService, private router:Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authSvc.isLoggedIn()
    this.protectedSvc.getListOfDeals()
      .then(result => {
        console.info(">>> HomeComponent: ngOnInit(): result: ", result)
        this.deals = result;
      })
      .catch(error => {
        console.error('>>> HomeComponent: ngOnInit(): error: ', error)
      })
  }

}
