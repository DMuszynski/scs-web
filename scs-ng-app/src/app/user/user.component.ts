import {AfterViewInit, Component, OnInit} from '@angular/core';
import {User} from "../model/user";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../services/user.service";
import {AuthenticationService} from "../services/authentication.service";
import {CharacterService} from "../services/character.service";
import {PaymentService} from "../services/payment.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit{

  private nickForm: FormGroup;
  private emailForm: FormGroup;
  private passwordForm: FormGroup;
  private purchaseForm: FormGroup;

  public isValidNick: boolean = true;
  public isValidEmail: boolean = true;
  public isValidPassword: boolean = true;
  public errorNick: String = '';
  public errorEmail: String = '';
  public errorPassword: String = '';

  private characters = [];
  private user: User = {
    id: 0,
    nick: '',
    email: '',
    password: '',
    premiumCurrency: 0,
  };

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private characterService: CharacterService,
    private authService: AuthenticationService,
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        if(params.paymentId != null) {
          this.paymentService.completePayment(params.paymentId, params.PayerID, {nick: localStorage.getItem("userToken")}).subscribe(
            value => {
              this.router.navigate(['user']);
            }
          );
          this.router.navigate(['finalizePayment']);
        }
      }
    );

    this.loadUser();
    this.nickForm = this.formBuilder.group({
      nick: [null, [
        Validators.required,
        Validators.pattern('^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$'),
        Validators.minLength(6),
        Validators.maxLength(15)
      ]],
    });
    this.emailForm = this.formBuilder.group({
      email: [null, [
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
        Validators.maxLength(30)
      ]],
    });
    this.passwordForm = this.formBuilder.group({
      password: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(30)
      ]],
    });
    this.purchaseForm = this.formBuilder.group({
      purchase: [null, [
        Validators.required,
        Validators.min(1),
        Validators.max(500)
      ]],
    });
  }

  public updateNick() {
    if(this.nickForm.valid && (this.user.nick !== this.updateUserNickForm.value)
      && confirm('Czy napewno chcesz zmienić nick na ' + this.updateUserNickForm.value + ' ?' )) {
      this.userService.updateUserNick(this.updateUserNickForm.value, this.user.id).subscribe(
        () => {
          this.authService.logOut();
        },
        error => {
          this.errorNick = error;
          this.isValidNick = false;
        }
      );
    }
  }

  public updateEmail() {
    if(this.emailForm.valid && (this.user.email !== this.updateUserEmailForm.value)
      && confirm('Czy napewno chcesz zmienić adres email na ' + this.updateUserEmailForm.value + ' ?')) {
      this.userService.updateUserEmail(this.updateUserEmailForm.value, this.user.id).subscribe(
        () => {
          this.authService.logOut();
        },
        error => {
          this.errorEmail = error;
          this.isValidEmail = false;
        }
      );
    }
  }

  public updatePassword() {
    if(this.passwordForm.valid && (this.user.password !== this.updateUserPasswordForm.value)
      && confirm('Czy napewno chcesz zmienić hasło ?')) {
      this.userService.updateUserPassword(this.updateUserPasswordForm.value, this.user.id).subscribe(
        () => {
          this.authService.logOut();
        },
        error => {
          this.errorPassword = error;
          this.isValidPassword = false;
        }
      );
    }
  }

  public purchasePremiumCurrency() {
    if(this.purchaseForm.valid && confirm('Czy napewno chcesz zakupić walutę premium ?')) {
      this.paymentService.makePayment(this.updateUserPurchaseForm.value).subscribe(
        response => {
          window.location.href = response.redirect_url;
        }
      );
    }
  }

  public deleteAccount(): void {
    if(confirm('Czy napewno chcesz usunąć konto ?')) {
      this.userService.deleteUser(this.user.id).subscribe(
        () => {
          this.authService.logOut();
        }
      );
    }
  }

  public deleteCharacter(id: number): void {
    if(confirm('Czy napewno chcesz usunąć postać ?')) {
      this.characterService.deleteCharacter(id).subscribe(
        () => {
          location.reload();
        }
      );
    }
  }

  private loadUser(): void {
    const username: string = localStorage.getItem('userToken');
    this.userService.getUser(username).subscribe(
      user => {
        this.user = user;
        this.loadUserCharacters();
      }
    );
  }

  private loadUserCharacters(): void {
    this.characterService.getUserCharacters(this.user.id).subscribe(
      characters => {
        this.characters = characters;
      }
    );
  }

  get updateUserNickForm(): AbstractControl { return this.nickForm.get('nick'); }
  get updateUserEmailForm(): AbstractControl { return this.emailForm.get('email'); }
  get updateUserPasswordForm(): AbstractControl { return this.passwordForm.get('password'); }
  get updateUserPurchaseForm(): AbstractControl { return this.purchaseForm.get('purchase'); }
}
