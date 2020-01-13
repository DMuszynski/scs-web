import { AfterViewInit, ChangeDetectorRef, Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { MdbTableDirective, MdbTablePaginationComponent } from "angular-bootstrap-md";
import { Product} from "../model/product";
import { ShopService } from "../services/shop.service";
import { Category } from "../model/category";
import { UserService } from "../services/user.service";
import {Transaction} from "../model/transaction";
import {User} from "../model/user";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit, AfterViewInit {

  @ViewChild(MdbTablePaginationComponent, { static: true }) private mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild(MdbTableDirective, { static: true }) private mdbTable: MdbTableDirective;

  private products: Array<Product> = Array<Product>();
  private categories: Array<Category> = Array<Category>();
  private previous: any = [];

  private visibleItemNumber: number = 5;
  private searchText: string = '';
  private selectedValue: number = 0;
  private premiumCurrency: number = 0;

  constructor(private cdRef: ChangeDetectorRef, private shopService: ShopService,
              private userService: UserService) { }

  ngOnInit() {
    this.getAllCategories();
    this.getAllItems();
    this.loadUserPremiumCurrency();
  }

  ngAfterViewInit() {
    this.mdbTablePagination.setMaxVisibleItemsNumberTo(this.visibleItemNumber);
    this.mdbTablePagination.calculateFirstItemIndex();
    this.mdbTablePagination.calculateLastItemIndex();
    this.cdRef.detectChanges();
  }

  public onSubmit(product: Product): void {
    if(confirm('Czy napewno chcesz kupić dany produkt ?')) {
      let transaction: Transaction = {
        product: product,
        user: { nick: localStorage.getItem("userToken").toString()}
      };

      this.shopService.realizeTransaction(transaction).subscribe(
        () => {
          location.reload();
        }
      );
    }
  }

  @HostListener('input')
  public onInput(): void {
    this.searchItems();
  }

  public getAllItems(): void {
    this.shopService.getAllProducts().subscribe(
      products => {
        this.products = products;
        this.updateDataSource();
      },
      () => {
        alert("An error has occurred;")
      }
    );
  }

  public getItemsByCategory(categoryId: number) {
    this.shopService.getProductsByCategory(categoryId).subscribe(
      products => {
        this.products = products;
        this.updateDataSource();
      },
      () => {
        alert("An error has occurred;")
      }
    );
  }

  public getAllCategories() {
    this.shopService.getAllCategories().subscribe(
      categories => {
        this.categories = categories;
      },
      () => {
        alert("Nie udało się wczytać kategorii")
      }
    )
  }

  public searchItems(): void {
    const prev = this.mdbTable.getDataSource();

    if (!this.searchText) {
      this.mdbTable.setDataSource(this.previous);
      this.products = this.mdbTable.getDataSource();
    }

    if (this.searchText) {
      this.products = this.mdbTable.searchLocalDataBy(this.searchText);
      this.mdbTable.setDataSource(prev);
    }
  }

  public getSelectedValue() {
    if (this.selectedValue == 0)
      this.getAllItems();
    else
      this.getItemsByCategory(this.selectedValue);
  }

  public isEnoughMoney(prize: number): boolean {
    return (prize <= this.premiumCurrency);
  }

  private updateDataSource() {
    this.mdbTable.setDataSource(this.products);
    this.products = this.mdbTable.getDataSource();
    this.previous = this.mdbTable.getDataSource();
  }

  private loadUserPremiumCurrency(): void {
    const username: string = localStorage.getItem('userToken');
    this.userService.getUser(username).subscribe(
      user => {
        this.premiumCurrency = user.premiumCurrency;
      }
    );
  }
}
