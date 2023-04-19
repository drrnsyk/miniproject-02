import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, firstValueFrom } from 'rxjs';
import { Account } from '../components/model/model';

@Injectable({
  providedIn: 'root'
})
export class ProtectedService {

  constructor(private http: HttpClient) {}

  // call backend to call API to get search game prices

  // call backend to call API to get selected game details

  // call backend to call API to get a list of stores

  // call backend to retrieve a list of existing accounts in DB for ADMIN user
  public getAccounts(): Promise<Account[]> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')
    
    return firstValueFrom<Account[]>(
      this.http.get<Account[]>('/api/dashboard/accounts', { headers: headers })
    )
  }

  // call backend to retrieve an account in DB for ADMIN user
  public getAccountById(id: string): Promise<Account> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')
    
    return firstValueFrom<Account>(
      this.http.get<Account>(`/api/dashboard/account/${id}`, { headers: headers })
    )
  }

  // call backend to update an account in DB for ADMIN user
  public updateAccountById(id: string, updatedAccount: Account): Promise<Account> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')
    
    return firstValueFrom<Account>(
      this.http.put<Account>(`/api/dashboard/account/update/${id}`, updatedAccount, { headers: headers })
    )
  }

  // call backend to perform delete on existing account in DB for ADMIN user
  public deleteAccountById(id: string): Promise<any> {
    const params = new HttpParams()
      .set("id", id)

    return firstValueFrom(
      this.http.delete('/api/dashboard/account/delete', { params })
    )
  }

  // call backend to perform CRUD on existing account in DB for ADMIN user

  // call backend to retrieve a count of existing accounts in DB for ADMIN user


} 
