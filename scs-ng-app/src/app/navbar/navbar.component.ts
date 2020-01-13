import {Component, OnInit} from '@angular/core';
import { ContactComponent } from "../modal/contact/contact.component";
import { DownloadComponent } from "../modal/download/download.component";
import { MDBModalRef, MDBModalService } from "angular-bootstrap-md";
import { RegisterComponent} from "../modal/register/register.component";
import { LoginComponent } from "../modal/login/login.component";
import { AuthenticationService } from "../services/authentication.service";
import { UserService } from "../services/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit{

  private modalRef: MDBModalRef;
  public premiumCurrency: number = 0;

  constructor(private modalService: MDBModalService, private userService: UserService,
              private authService: AuthenticationService) { }

  ngOnInit(): void {
    if(this.isUserLoggedIn())
      this.loadUserPremiumCurrency();
  }

  public openDownloadModal(): void {
    this.modalRef = this.modalService.show(DownloadComponent);
  }

  public openContactModal(): void {
    this.modalRef = this.modalService.show(ContactComponent);
  }

  public openRegisterModal(): void {
    this.modalRef = this.modalService.show(RegisterComponent);
  }

  public openLoginModal(): void {
    this.modalRef = this.modalService.show(LoginComponent);
  }

  public isUserLoggedIn(): boolean {
    return this.authService.isUserLoggedIn();
  }

  public logOut(): void {
    this.authService.logOut();
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
