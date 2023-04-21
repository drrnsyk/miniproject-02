import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { ProtectedService } from 'src/app/service/protected.service';
import { Game } from '../model/model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  email!: string
  searchForm!: FormGroup
  games: Game[] = []
  searchTerm!: string

  constructor(private authSvc: AuthService, private fb: FormBuilder, private protectedSvc: ProtectedService, private router: Router) {}

  ngOnInit(): void {
      this.email = this.authSvc.getEmail()??""
      this.searchForm = this.createForm()
  }

  logout() {
    this.authSvc.logout()
  }

  doSearch() {
    this.searchTerm = this.searchForm.get('searchTerm')?.value
    console.info('>>> SearchComponent: doSearch(): searchTerm: ', this.searchTerm)
    this.protectedSvc.getListOfGames(this.searchTerm)
      .then(result => { 
        console.info('>>> SearchComponent: doSearch(): result: ', result)
        this.games = result
      })
      .catch(error => {
        console.error('>>> SearchComponent: doSearch(): error: ', error)
      })
  }

  doMore(dealID: string) {
    this.router.navigate([ '/api/deal/detail', dealID])
  }

  private createForm() {
    return this.fb.group({
      searchTerm: this.fb.control('', Validators.required)
    })
  }


}
