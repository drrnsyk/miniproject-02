import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  email!: string

  constructor(private authSvc: AuthService) {}

  ngOnInit(): void {
      this.email = this.authSvc.getEmail()??""
  }

  logout() {
    this.authSvc.logout()
  }


}
