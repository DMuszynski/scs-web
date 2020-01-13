import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/internal/Observable";
import { environment } from "../../environments/environment";

@Injectable({ providedIn: 'root' })
export class FileService {

  private BASE_URL = environment.baseUrl;
  private DOWNLOAD_CLIENT = this.BASE_URL + '/download/client/';

  constructor(private http: HttpClient) {}

  public downloadClient(filename: string): Observable<Blob>{
    return this.http.get(this.DOWNLOAD_CLIENT + filename, { responseType: 'blob' });
  }
}
