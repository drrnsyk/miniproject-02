import { Component, OnInit, ViewChild } from '@angular/core';
import { ProtectedService } from 'src/app/service/protected.service';
import { Account } from '../model/model';
import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
import { AuthService } from 'src/app/service/auth.service';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  accounts: Account[] = []
  dataSource: MatTableDataSource<Account>;
  displayedColumns: string[] = ['id', 'name', 'email', 'role', 'edit', 'delete']; // Replace with actual column names in your Account model
  pageSizeOptions: number[] = [5, 10, 25]; // Specify the page size options
  email!: string

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private protectedSvc: ProtectedService, private authSvc: AuthService, private router:Router) {
    this.dataSource = new MatTableDataSource<Account>(this.accounts);
  }

  ngOnInit(): void {
      this.protectedSvc.getAccounts()
        .then(result => {
          console.info('>>> DashboardComponent: result: ', result)
          this.accounts = result
          this.dataSource.data = this.accounts; // Update the data source with the retrieved accounts
        })
        .catch(error => {
          console.error('>>> DashboardComponent: error: ', error)
        })

      this.email = this.authSvc.getEmail()??""
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.protectedSvc.getAccounts()
    .then(result => {
      console.info('>>> DashboardComponent: result: ', result)
      this.accounts = result
      this.dataSource.data = this.accounts; // Update the data source with the retrieved accounts
    })
    .catch(error => {
      console.error('>>> DashboardComponent: error: ', error)
    })

  }

  doEditAccount(id: string) {
    this.router.navigate([ '/api/dashboard/account', id ])
  }

  doDeleteAccount(id: string) {
    this.router.navigate([ '/api/dashboard/account', id, 'delete' ])
  }

  onPageChange(event: PageEvent): void {
    // Handle pagination event
    const pageIndex = event.pageIndex;
    const pageSize = event.pageSize;
    // You can use the pageIndex and pageSize to fetch data from backend if needed
  }


}
