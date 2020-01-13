import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { AuthenticationService } from "../../services/authentication.service";
import { Router } from "@angular/router";
import { MDBModalRef } from "angular-bootstrap-md";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-modal-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  isValidLogin: boolean = true;
  errorMessage = '';

  constructor(private formBuilder: FormBuilder, private router: Router, public modalRef: MDBModalRef,
              private userService: UserService, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
        nick: [null, [
          Validators.required,
          Validators.pattern('^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$'),
          Validators.minLength(6),
          Validators.maxLength(20)
        ]],
        password: [null, [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(30)
        ]],
    });
  }

  public onSubmit(): void {
    if(this.loginForm.valid) {
      this.authenticationService.login(this.loginForm.value).subscribe(
        () => {
            localStorage.setItem('userToken', this.loginFormModalNick.value);

            this.isValidLogin = true;
            this.modalRef.hide();
            this.router.navigate(['user']);
        },
        error => {
          this.errorMessage = error;
          this.isValidLogin = false;
          this.loginForm.reset();
        }
      );
    }
  }

  public get loginFormModalNick(): AbstractControl { return this.loginForm.get('nick'); }
  public get loginFormModalPassword(): AbstractControl { return this.loginForm.get('password'); }
}
