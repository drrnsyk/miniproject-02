import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { AccountData } from '../components/model/model';

@Injectable({
  providedIn: 'root'
})
export class ProtectedService {

  constructor(private http: HttpClient) {}

  // call backend to call API to get search game prices

  // call backend to call API to get selected game details

  // call backend to call API to get a list of stores

  // call backend to retrieve a list of existing accounts in DB for ADMIN user
  public getAccountsData(): Promise<AccountData[]> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')
    
    return firstValueFrom<AccountData[]>(
      this.http.get<AccountData[]>('/api/admin/accounts', { headers: headers })
    )

  }

  // call backend to perform CRUD on existing admins account in DB for ADMIN user

  // call backend to retrieve a count of existing accounts in DB for ADMIN user


} 
