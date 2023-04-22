import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { loadStripe } from '@stripe/stripe-js';
import { ProtectedService } from 'src/app/service/protected.service';

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent {

  // We load  Stripe
  stripePromise = loadStripe('pk_test_51MzbTxI9k83KmxCrHNfp8J0VSpMR1zuofzXOOAhFEWNl3nCsAufN7zCr5HDnDdoNznReAtaBdV68lKIHTkE7PFEz003178FAFT');
  
  constructor(private http: HttpClient, private protectedSvc: ProtectedService) {}

  // async pay(): Promise<void> {
  //   // here we create a payment object
  //   const payment = {
  //     name: 'one-time-subscription',
  //     currency: 'usd',
  //     // amount on cents *10 => to be on dollar
  //     amount: 49900,
  //     quantity: '1',
  //     cancelUrl: 'http://localhost:4200/cancel',
  //     successUrl: 'http://localhost:4200/success',
  //   };

  //   const stripe = await this.stripePromise;

  //   // this is a normal http calls for a backend api
  //   console.info('>>> SubscribeComponent: pay(): calls the backend')
  //   this.http
  //     .post('/sub/subscribe', payment)
  //     .subscribe((data: any) => {
  //       // I use stripe to redirect To Checkout page of Stripe platform
  //       // stripe?.redirectToCheckout({
  //       //   sessionId: data.id,
  //       // });
  //       console.info('>>> SubscribeComponent: pay(): data', data)
  //     });
  // }

  async doSubscribe() {

    const payment = {
      name: 'one-time-subscription',
      currency: 'usd',
      amount: 4990,
      quantity: '1',
      cancelUrl: 'http://localhost:4200/#/sub/subscribe',
      successUrl: 'http://localhost:4200/#/auth/signup',
    };

    const stripe = await this.stripePromise;

    this.protectedSvc.subscribe(payment)
      .then(result => {
        console.info(">>> SubscribeComponent: doSubscribe(): result: ", result)
        stripe?.redirectToCheckout({
          sessionId: result.id,
        });
      })
      .catch(error => {
        console.error('>>> SubscribeComponent: doSubscribe(): error: ', error)
      })
  }


}
