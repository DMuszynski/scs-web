import {AfterViewInit, Component, Input} from '@angular/core';
import { MDBModalRef, MDBModalService } from "angular-bootstrap-md";
import {DownloadComponent} from "../modal/download/download.component";

declare var jarallax: any;

@Component({
  selector: 'app-intro',
  templateUrl: './intro.component.html'
})
export class IntroComponent implements AfterViewInit{

  @Input() private height: string;
  @Input() private backgroundImageUrl: string;
  private modalRef: MDBModalRef;

  constructor(private modalService: MDBModalService) { }

  ngAfterViewInit() {
    jarallax(document.querySelectorAll('.jarallax'), {
      speed: 0.2
    });
  }

  public openDownloadModal(): void {
    this.modalRef = this.modalService.show(DownloadComponent);
  }
}
