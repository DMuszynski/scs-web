import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { UserService } from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-modal-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  private registerForm: FormGroup;
  public isValidRegister: boolean = false;
  public isInvalidRegister: boolean = false;
  public responseMessage: string = '';

  constructor(private formBuilder: FormBuilder, private router: Router,
              private userService: UserService) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      nick: [null, [
        Validators.required,
        Validators.pattern('^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$'),
        Validators.minLength(6),
        Validators.maxLength(20)
      ]],
      email: [null, [
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
        Validators.minLength(10),
        Validators.maxLength(35)
      ]],
      password: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(30)
      ]],
    });
  }

  public onSubmit() {
    if(this.registerForm.valid) {
      this.userService.register(this.registerForm.value).subscribe(
        () => {
          this.responseMessage = 'Dziękujemy za rejestracje! Wysłaliśmy link aktywacyjny na twoją poczte.';
          this.isValidRegister = true;
          this.registerForm.reset();
        },
        error => {
          this.responseMessage = error;
          this.isInvalidRegister = true;
          this.registerForm.reset();
        }
      );
    }
  }

  public get registerFormModalNick(): AbstractControl { return this.registerForm.get('nick'); }
  public get registerFormModalEmail(): AbstractControl { return this.registerForm.get('email'); }
  public get registerFormModalPassword(): AbstractControl { return this.registerForm.get('password'); }
}
