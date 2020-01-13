import { Injectable } from '@angular/core';
import { Observable } from "rxjs/internal/Observable";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private BASE_URL = environment.baseUrl;
  private LOGIN_USER_URL = this.BASE_URL + '/login';

  constructor(private http: HttpClient, private router: Router) {}

  public login(auth: {nick: string, password: string}): Observable<any> {
    return this.http.post(this.LOGIN_USER_URL, auth);
  }

  public isUserLoggedIn(): boolean {
    let token = localStorage.getItem('userToken');
    return !(token === null);
  }

  public logOut(): void {
    localStorage.removeItem('userToken');
    this.router.navigate(['/home']);
  }
}
