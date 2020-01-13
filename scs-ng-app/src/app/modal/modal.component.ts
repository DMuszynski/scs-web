import {Component, OnInit } from '@angular/core';
import {MDBModalRef} from "angular-bootstrap-md";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent{

  constructor(public modalRef: MDBModalRef) {}

  closeModal() { this.modalRef.hide(); }
}
