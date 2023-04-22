import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Account, Deal, Detail, Game, Store, Stripeid } from '../components/model/model';

@Injectable({
  providedIn: 'root'
})
export class ProtectedService {

  constructor(private http: HttpClient) {}

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

  // call backend to add a new admin account in DB for ADMIN user
  // call backend to retrieve a count of existing accounts in DB for ADMIN user

  // call backend to call API to get a list of deals
  public getListOfDeals(): Promise<Deal[]> {
    return firstValueFrom<Deal[]>(
      this.http.get<Deal[]>('/api/home')
    )
  }

  // call backend to call API to get a list of games
  public getListOfGames(searchTerm: string): Promise<Game[]> {
    const params = new HttpParams()
      .set("searchTerm", searchTerm)

    return firstValueFrom<Game[]>(
      this.http.get<Game[]>('/api/search', { params })
    )
  }

  // call backend to call API to get selected deal detail
  public getDealDetail(dealID: string): Promise<Detail> {
    const params = new HttpParams()
      .set("dealID", dealID)

    return firstValueFrom<Detail>(
      this.http.get<Detail>('/api/deal/detail', { params })
    )
  }

  // call backend to call API to get list of stores
  public getListOfStores(): Promise<Store[]> {
    return firstValueFrom<Store[]>(
      this.http.get<Store[]>('/api/stores')
    )
  }

  // call backend to create session token with stripe
  public subscribe(payment: any): Promise<Stripeid> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json')

    return firstValueFrom<Stripeid>(
      this.http.post<Stripeid>('/sub/subscribe', payment, { headers: headers })
    )
  }
} 
