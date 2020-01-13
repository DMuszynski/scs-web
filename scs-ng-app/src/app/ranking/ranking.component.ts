import {AfterViewInit, ChangeDetectorRef, Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {MdbTableDirective, MdbTablePaginationComponent} from "angular-bootstrap-md";
import {Character} from "../model/character";
import {CharacterService} from "../services/character.service";

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html'
})
export class RankingComponent implements OnInit, AfterViewInit {

  @ViewChild(MdbTablePaginationComponent, { static: true }) private mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild(MdbTableDirective, { static: true }) private mdbTable: MdbTableDirective;

  private characters: Array<Character> = Array<Character>();
  private previous: any = [];
  private headElements = ['POZYCJA', 'NAZWA POSTACI', 'WYNIK'];

  private visibleItemNumber: number = 20;
  private searchText: string = '';

  constructor(private cdRef: ChangeDetectorRef, private characterService: CharacterService) { }

  @HostListener('input') onInput() {
    this.searchItems();
  }

  ngOnInit() {
    this.loadUsersCharacters();
  }

  ngAfterViewInit() {
    this.mdbTablePagination.setMaxVisibleItemsNumberTo(this.visibleItemNumber);
    this.mdbTablePagination.calculateFirstItemIndex();
    this.mdbTablePagination.calculateLastItemIndex();
    this.cdRef.detectChanges();
  }

  public calculateRankPosition(score: number): number {
    for (let i = 0; i < this.characters.length; i++) {
      if(this.characters[i].score <= score)
        return (i + 1);
    }
  }

  private loadUsersCharacters() {
    this.characterService.getCharacters().subscribe(
      characters => {
        this.characters = this.getSortedArray(characters);

        this.mdbTable.setDataSource(this.characters);
        this.characters = this.mdbTable.getDataSource();
        this.previous = this.mdbTable.getDataSource();
      }
    );
  }

  private getSortedArray(characters: Character[]) {
    return characters.sort((a,b) => {
      return (b.score - a.score)
    });
  }

  private searchItems() {
    const prev = this.mdbTable.getDataSource();

    if (!this.searchText) {
      this.mdbTable.setDataSource(this.previous);
      this.characters = this.mdbTable.getDataSource();
    }

    if (this.searchText) {
      this.characters = this.mdbTable.searchLocalDataBy(this.searchText);
      this.mdbTable.setDataSource(prev);
    }
  }
}
