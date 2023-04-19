import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ProtectedService } from 'src/app/service/protected.service';

@Component({
  selector: 'app-accounddel',
  templateUrl: './accounddel.component.html',
  styleUrls: ['./accounddel.component.css']
})
export class AccounddelComponent implements OnInit, OnDestroy {

  id = ''
  params$!: Subscription

  constructor(private protectedSvc: ProtectedService,private activatedRoute: ActivatedRoute, private router:Router) {}

  ngOnInit(): void {
    console.info(">>> AccoundDelComponent: ngOnInit called.")
    this.params$ = this.activatedRoute.params.subscribe(
      (params)=>{
        this.id = params['id']
        console.info('>>> AccoundDelComponent: id: ', this.id)
      }
    )
  }

  doYes(id: string) {
    this.protectedSvc.deleteAccountById(id)
    
    this.router.navigate(['/api/dashboard'])
  }

  doNo() {
    this.router.navigate(['/api/dashboard'])
  }

  ngOnDestroy(): void {
    this.params$.unsubscribe()
  } 

}
