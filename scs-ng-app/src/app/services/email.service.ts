import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";
import { environment } from "../../environments/environment";
import { Email } from "../model/email";

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private BASE_URL = environment.baseUrl + '/email';
  private POST_EMAIL_URL = this.BASE_URL + '/send';

  constructor(private http: HttpClient) { }

  public postEmail(email: Email): Observable<Email> {
    return this.http.post<Email>(this.POST_EMAIL_URL, email);
  }
}
