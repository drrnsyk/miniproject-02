import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from 'src/app/service/signup.service';
import { Match } from 'src/app/validation/match.validator';
import { Status } from '../model/model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm!: FormGroup
  status!: Status;

  constructor(private fb: FormBuilder, private signupSvc: SignupService, private router:Router) {
    const paymentSuccess = sessionStorage.getItem('paymentSuccess');
    if (!paymentSuccess) {
      this.router.navigate([ '/sub/subscribe' ]);
    }
  }

  ngOnInit(): void {
    this.signupForm = this.createForm()
  }

  doPost() {
    console.info('>>> signupForm: ngSubmit(): ', this.signupForm)
    this.status = {statusCode: 0, message:"wait..."};
    this.signupSvc.signup(this.signupForm.value)
      .then(result => {
        console.info('>>> signup(): result: ', result)
        this.status.statusCode = 200
        this.status = result
      })
      .catch(error => {
        console.error('>>> signup(): error: ', error)
        this.status.statusCode = 0
        this.status.message = "Registration Unsuccessful!"
      })
    this.signupSvc.sendWelcomeEmail(this.signupForm.value)
      .then(result => {
        console.info('>>> sendWelcomeEmail(): result: ', result)
      })
      .catch(error => {
        console.error('>>> sendWelcomeEmail(): error: ', error)
      })
    this.router.navigate([ '/auth/login' ])
  }

  private createForm() {
    return this.fb.group({
      name: this.fb.control('', Validators.required),
      email: this.fb.control('', [ Validators.required, Validators.email ] ),
      password: this.fb.control('', Validators.required),
      confirmPassword: this.fb.control('', Validators.required),
      role: this.fb.control('USER', Validators.required)
    },{
      validators: Match('password', 'confirmPassword')
    })
  }

}
