import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { ProtectedService } from 'src/app/service/protected.service';
import { Deal, Store } from '../model/model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn!: boolean
  deals: Deal[] = []
  stores: Store[] = []
  dealsWithStores: any[] = [];

  constructor(private authSvc: AuthService, private protectedSvc: ProtectedService, private router:Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authSvc.isLoggedIn()
    this.protectedSvc.getListOfStores()
      .then(result => {
        console.info(">>> HomeComponent: getListOfStores: result: ", result)
        this.stores = result;
        this.protectedSvc.getListOfDeals()
        .then(result => {
          console.info(">>> HomeComponent: getListOfDeals(): result: ", result)
          this.deals = result;
          console.info(">>> HomeComponent: getListOfDeals(): stores: ", this.stores)
          this.dealsWithStores = this.deals.map(deal => {
            const store = this.stores.find(store => store.storeID == deal.storeID);
            return {
              ...deal,
              storeName: store?.storeName,
              storeLogo: store?.imagesLogo
            };
          });
        })
        .catch(error => {
          console.error('>>> HomeComponent: ngOnInit(): error: ', error)
        })
      })
      .catch(error => {
        console.error('>>> HomeComponent: getListOfStores: error: ', error)
      })
  }

  doSubscribe() {
    this.router.navigate([ '/sub/subscribe' ])
  }

}
