import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ProtectedService } from 'src/app/service/protected.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit, OnDestroy {

  dealID = ''
  params$!: Subscription

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private protectedSvc: ProtectedService, ) {}

  ngOnInit(): void {
    console.info(">>> DetailComponent: ngOnInit called.")
    this.params$ = this.activatedRoute.params.subscribe(
      (params)=>{
        this.dealID = params['dealID']
        console.info('>>> DetailComponent: ngOnInit(): dealID: ', this.dealID)
      }
    )
    this.protectedSvc.getDealDetail(this.dealID)
      .then(result => {
        console.info('>>> DetailComponent: result: ', result)
        this.router.navigate(['/'])
      })
      .catch(error => {
        console.error('>>> DetailComponent: error: ', error)
      })
  }


  
  ngOnDestroy(): void {
    this.params$.unsubscribe()
  }

}
