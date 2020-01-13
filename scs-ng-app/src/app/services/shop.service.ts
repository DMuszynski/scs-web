import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";
import { Product } from "../model/product";
import { Category } from "../model/category";
import { environment } from "../../environments/environment";
import {Transaction} from "../model/transaction";

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  private BASE_URL = environment.baseUrl;
  private GET_ALL_PRODUCTS_URL = this.BASE_URL + '/categories/products';
  private GET_PRODUCTS_BY_CATEGORY_URL = this.BASE_URL + '/categories/';
  private GET_ALL_CATEGORIES_URL = this.BASE_URL + '/categories';
  private REALIZE_TRANSACTION = this.BASE_URL + '/transaction';

  constructor(private http: HttpClient) { }

  public getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.GET_ALL_CATEGORIES_URL);
  }

  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.GET_ALL_PRODUCTS_URL);
  }

  public getProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.GET_PRODUCTS_BY_CATEGORY_URL + categoryId + '/products');
  }

  public realizeTransaction(transaction: Transaction): Observable<any> {
    return this.http.post(this.REALIZE_TRANSACTION, transaction);
  }
}
