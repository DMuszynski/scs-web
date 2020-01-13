import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-start-server',
  templateUrl: './start-server.component.html'
})
export class StartServerComponent implements OnInit {

  private startServerDate: Date;

  constructor() {}

  ngOnInit() {
    this.startServerDate = new Date('2020-02-01 GMT+2 08:00:00');
  }

  private countMsToStartFromDate(start: Date): number  {
    return (start.getTime() - new Date().getTime());
  }

  private countMinToStart(): number {
    const msFromHourToStart = ((this.hourToStart() * 1000) * 3600);
    const msFromMinToStart = ((this.countHourToStart() * 1000) * 3600) - msFromHourToStart;

    return ((msFromMinToStart / 1000) / 60);
  }

  private countHourToStart(): number {
    const msFromDayToStart = (((this.dayToStart() * 1000) * 3600) * 24);
    const msFromHourToStart = this.countMsToStartFromDate(this.startServerDate) - msFromDayToStart;

    return ((msFromHourToStart / 1000) / 3600);
  }

  private countDayToStart(): number {
    return (((this.countMsToStartFromDate(this.startServerDate) / 1000) / 3600 ) / 24);
  }

  public minToStart(): number {
    return parseInt(this.countMinToStart().toString(), 0);
  }

  public hourToStart(): number {
    return parseInt(this.countHourToStart().toString(), 0);
  }

  public dayToStart(): number {
    return parseInt(this.countDayToStart().toString(), 0);
  }
}
