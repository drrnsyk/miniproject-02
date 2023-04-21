import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from 'src/app/service/signup.service';
import { Match } from 'src/app/validation/match.validator';
import { Status } from '../model/model';

@Component({
  selector: 'app-accountadd',
  templateUrl: './accountadd.component.html',
  styleUrls: ['./accountadd.component.css']
})
export class AccountaddComponent implements OnInit{

  addForm!: FormGroup
  status!: Status;

  constructor(private fb: FormBuilder, private signupSvc: SignupService, private router: Router) {}

  ngOnInit(): void {
      this.addForm = this.createForm()
  }

  doPost() {
    console.info('>>> addForm: ngSubmit(): ', this.addForm)
    this.status = {statusCode: 0, message:"wait..."};
    this.signupSvc.signup(this.addForm.value)
      .then(result => {
        console.info('>>> addForm: result: ', result)
        this.status.statusCode = 200
        this.status = result
        alert("Registration Successful!")
        this.router.navigate([ '/api/dashboard' ])
      })
      .catch(error => {
        console.error('>>> addForm: error: ', error)
        this.status.statusCode = 0
        this.status.message = "Registration Unsuccessful!"
        alert("Registration Unsuccessful!")
      })
  }

  private createForm() {
    return this.fb.group({
      name: this.fb.control('', Validators.required),
      email: this.fb.control('', [ Validators.required, Validators.email ] ),
      password: this.fb.control('', Validators.required),
      confirmPassword: this.fb.control('', Validators.required),
      role: this.fb.control('ADMIN', Validators.required)
    },{
      validators: Match('password', 'confirmPassword')
    })
  }

}
