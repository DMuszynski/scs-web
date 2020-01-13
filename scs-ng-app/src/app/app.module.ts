import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MDBBootstrapModule, MDBModalRef} from 'angular-bootstrap-md';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './modal/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './modal/register/register.component';
import { ContactComponent } from './modal/contact/contact.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './home/about/about.component';
import { ServicesComponent } from './home/services/services.component';
import { StartServerComponent } from './home/start-server/start-server.component';
import { RequirementsComponent } from './home/requirements/requirements.component';
import { IntroComponent } from './intro/intro.component';
import { UserService } from "./services/user.service";
import { AuthenticationService } from "./services/authentication.service";
import { UserComponent } from './user/user.component';
import { ModalComponent } from './modal/modal.component';
import { DownloadComponent } from "./modal/download/download.component";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { ShopComponent } from './shop/shop.component';
import { RankingComponent } from './ranking/ranking.component';
import { CardComponent } from './user/card/card.component';
import { ShopService } from "./services/shop.service";
import { AuthGuardService } from "./services/auth-guard.service";
import { HttpErrorInterceptor } from "./interceptor/http-error.interceptor";
import { EmailService } from "./services/email.service";
import { FinalizePaymentComponent } from './finalize-payment/finalize-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ContactComponent,
    DownloadComponent,
    HomeComponent,
    AboutComponent,
    ServicesComponent,
    StartServerComponent,
    RequirementsComponent,
    IntroComponent,
    UserComponent,
    ModalComponent,
    ShopComponent,
    RankingComponent,
    CardComponent,
    FinalizePaymentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    UserService,
    ShopService,
    AuthenticationService,
    AuthGuardService,
    EmailService,
    MDBModalRef,
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
  entryComponents: [
    ModalComponent,
    LoginComponent,
    RegisterComponent,
    ContactComponent,
    DownloadComponent
  ]
})
export class AppModule { }
