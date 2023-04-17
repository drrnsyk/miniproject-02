import { Component, OnInit } from '@angular/core';
import { ProtectedService } from 'src/app/service/protected.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private protectedSvc: ProtectedService) {}

  ngOnInit(): void {
      this.protectedSvc.getAccountsData()
        .then(result => {
          console.info('>>> DashboardComponent: result: ', result)
        })
        .catch(error => {
          console.error('>>> DashboardComponent: error: ', error)
        })
  }


}
