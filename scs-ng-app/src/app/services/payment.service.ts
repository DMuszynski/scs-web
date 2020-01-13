import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment";
import { Observable } from "rxjs/internal/Observable";
import {User} from "../model/user";

@Injectable({ providedIn: 'root' })
export class PaymentService {

  private BASE_URL = environment.baseUrl;
  private MAKE_PAYMENT = this.BASE_URL + '/paypal/make/';
  private COMPLETE_PAYMENT  = this.BASE_URL + '/paypal/complete/';

  constructor(private http: HttpClient) {}

  public makePayment(sum): Observable<any> {
    return this.http.post(this.MAKE_PAYMENT + 'payment?sum=' + sum, {});
  }

  public completePayment(paymentId, payerId, user: User): Observable<any> {
    return this.http.post(this.COMPLETE_PAYMENT + 'payment?paymentId=' + paymentId + '&PayerID=' + payerId , user);
  }
}
