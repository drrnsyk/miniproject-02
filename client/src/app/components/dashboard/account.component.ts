import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ProtectedService } from 'src/app/service/protected.service';
import { Account } from '../model/model';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit, OnDestroy {

  id = ''
  params$!: Subscription
  // account!: Account
  accountForm!: FormGroup
  account: Account = { id: '', name: '', email: '', password: '', role: ''}

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private protectedSvc: ProtectedService, private fb: FormBuilder) {}

  ngOnInit(): void {
    console.info(">>> AccountComponent: ngOnInit called.")
    this.params$ = this.activatedRoute.params.subscribe(
      (params)=>{
        this.id = params['id']
        console.info('>>> AccountComponent: id: ', this.id)
      }
    )
    this.protectedSvc.getAccountById(this.id)
    .then(result => {
      console.info('>>> AccountComponent: result: ', result)
      this.account = result

      this.accountForm = this.fb.group({
        id: this.fb.control(this.account.id, Validators.required),
        name: this.fb.control(this.account.name, Validators.required),
        email: this.fb.control(this.account.email, Validators.required),
        password: this.fb.control(this.account.password, Validators.required),
        role: this.fb.control(this.account.role, Validators.required)
      })

      // this.accountForm = this.fb.group({
      //   email: [this.account.email, Validators.required],
      //   name: [this.account.name, Validators.required],
      // })
      
      // this.accountForm.patchValue({
      //   name: this.account.name,
      //   email: this.account.email
      // })

    })
    .catch(error => {
      console.error('>>> AccountComponent: error: ', error)
    })
  }

  doUpdateAccount() {
    const updatedAccount: Account = this.accountForm.value as Account
    console.info('>>> AccountComponent: doUpdateAccount(): updatedAccount: ', updatedAccount)
    this.protectedSvc.updateAccountById(this.id, updatedAccount)
      .then(result => {
        console.info('>>> AccountComponent: doUpdateAccount(): result: ', result)
        alert("Account successfully updated!")
        this.router.navigate(['/api/dashboard'])
      })
      .catch(error => {
        console.error('>>> AccountComponent: doUpdateAccount(): error: ', error)
      })
  }

  doCancel() {
      this.router.navigate(['/api/dashboard'])
  }

  ngOnDestroy(): void {
      this.params$.unsubscribe()
  }

}
