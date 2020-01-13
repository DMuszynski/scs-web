import { Component } from '@angular/core';
import { MDBModalRef, MDBModalService } from "angular-bootstrap-md";
import { ContactComponent } from "../modal/contact/contact.component";
import { DownloadComponent } from "../modal/download/download.component";
import { RegisterComponent } from "../modal/register/register.component";
import { LoginComponent } from "../modal/login/login.component";
import { AuthenticationService } from "../services/authentication.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html'
})
export class FooterComponent {

  private modalRef: MDBModalRef;

  constructor(private modalService: MDBModalService, private authService: AuthenticationService) {}

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

  public isUserLoggedIn() {
    return this.authService.isUserLoggedIn();
  }
}
