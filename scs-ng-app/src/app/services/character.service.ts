import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";
import { environment } from "../../environments/environment";
import {Character} from "../model/character";

@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  private BASE_URL = environment.baseUrl;
  private GET_CHARACTERS_URL = this.BASE_URL + '/users/characters';
  private GET_USER_CHARACTERS_URL = this.BASE_URL + '/user/';
  private DELETE_CHARACTER_URL = this.BASE_URL + '/user/characters/';

  constructor(private http: HttpClient) {}

  public getCharacters(): Observable<any> {
    return this.http.get(this.GET_CHARACTERS_URL);
  }

  public getUserCharacters(idUser: number): Observable<any> {
    return this.http.get(this.GET_USER_CHARACTERS_URL + idUser + '/characters');
  }

  public deleteCharacter(idCharacter: number): Observable<Character> {
    return this.http.delete(this.DELETE_CHARACTER_URL + idCharacter);
  }
}
