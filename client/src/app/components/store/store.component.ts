import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProtectedService } from 'src/app/service/protected.service';
import { Store } from '../model/model';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit{

  stores: Store[] = []

  constructor(private protectedSvc: ProtectedService, private router:Router) {}

  ngOnInit(): void {
    this.protectedSvc.getListOfStores()
      .then(result => {
        console.info(">>> StoreComponent: ngOnInit(): result: ", result)
        this.stores = result;
      })
      .catch(error => {
        console.error('>>> StoreComponent: ngOnInit(): error: ', error)
      })
  }

}
