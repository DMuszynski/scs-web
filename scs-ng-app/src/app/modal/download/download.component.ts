import { Component } from '@angular/core';
import { FileService } from "../../services/file.service";
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-download',
  templateUrl: './download.component.html',
  styleUrls: ['./download.component.scss']
})
export class DownloadComponent {

  constructor(private fileService: FileService) { }

  public onSubmit(): void {
    this.fileService.downloadClient('scs-client').subscribe(
      client => {
        const newBlob = new Blob([client], { type: "application/zip" });
        saveAs(newBlob, 'scs-client.zip');
    });
  }
}
