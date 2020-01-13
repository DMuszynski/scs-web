import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { EmailService } from "../../services/email.service";

@Component({
  selector: 'app-modal-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  private contactForm: FormGroup;
  public isValidContact: boolean = false;
  public isInvalidContact: boolean = false;
  public responseMessage: string = '';

  constructor(private formBuilder: FormBuilder, private emailService: EmailService) { }

  ngOnInit(): void {
    this.contactForm = this.formBuilder.group({
        from: [null, [
          Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
          Validators.minLength(10),
          Validators.maxLength(35)
        ]],
        subject: [null, [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]],
        content: [null, [
          Validators.required,
          Validators.minLength(25),
          Validators.maxLength(250)
        ]]
    });
  }

  public onSubmit(): void {
    if(this.contactForm.valid) {
      this.emailService.postEmail(this.contactForm.value).subscribe(
        () => {
          this.responseMessage = 'Dziękujemy za zgłoszenie! Postaramy się je jak najszybciej rozpatrzyć.';
          this.isValidContact = true;
          this.contactForm.reset();
        },
        error => {
          this.responseMessage = error;
          this.isInvalidContact = true;
          this.contactForm.reset();
        }
      );
    }
  }

  public get contactFormModalEmailFrom(): AbstractControl { return this.contactForm.get('from'); }
  public get contactFormModalSubject(): AbstractControl { return this.contactForm.get('subject'); }
  public get contactFormModalContent(): AbstractControl { return this.contactForm.get('content'); }
}
