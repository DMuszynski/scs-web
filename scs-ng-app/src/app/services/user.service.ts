import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { User } from "../model/user";
import { Observable } from "rxjs/internal/Observable";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private BASE_URL = environment.baseUrl;
  private REGISTER_USER_URL = this.BASE_URL + '/register';
  private GET_USER_URL = this.BASE_URL + '/user/';
  private UPDATE_USER_NICK = this.BASE_URL + '/user/update/nick';
  private UPDATE_USER_EMAIL = this.BASE_URL + '/user/update/email';
  private UPDATE_USER_PASSWORD = this.BASE_URL + '/user/update/password';
  private DELETE_USER = this.BASE_URL + '/user/delete/';

  constructor(private http: HttpClient) {}

  public register(user: User): Observable<any> {
    return this.http.post(this.REGISTER_USER_URL , user);
  }
  public getUser(nick: String): Observable<User> {
    return this.http.get<User>(this.GET_USER_URL + nick);
  }
  public updateUserNick(nick: String, id: number): Observable<User> {
    return this.http.patch<User>(this.UPDATE_USER_NICK, {nick, id});
  }
  public updateUserEmail(email: String, id: number): Observable<User> {
    return this.http.patch<User>(this.UPDATE_USER_EMAIL, {email, id});
  }
  public updateUserPassword(password: String, id: number): Observable<User> {
    return this.http.patch<User>(this.UPDATE_USER_PASSWORD, {password, id});
  }
  public deleteUser(id: number): Observable<User> {
    return this.http.delete<User>(this.DELETE_USER + id);
  }
}
