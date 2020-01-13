import { Component } from '@angular/core';
import { MDBModalRef, MDBModalService } from "angular-bootstrap-md";
import {DownloadComponent} from "../../modal/download/download.component";

@Component({
  selector: 'app-requirements',
  templateUrl: './requirements.component.html'
})
export class RequirementsComponent {

  private modalRef: MDBModalRef;

  constructor(private modalService: MDBModalService) { }

  public openDownloadModal(): void {
    this.modalRef = this.modalService.show(DownloadComponent);
  }
}
