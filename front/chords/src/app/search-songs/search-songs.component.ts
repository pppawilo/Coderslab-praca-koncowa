import { Component, OnInit } from "@angular/core";
import { SongService } from "../song.service";
import { Song } from "../song";

@Component({
  selector: "app-search-songs",
  templateUrl: "./search-songs.component.html",
  styleUrls: ["./search-songs.component.css"]
})
export class SearchSongsComponent implements OnInit {
  id: number;
  songs: Song[];

  constructor(private dataService: SongService) {}

  ngOnInit() {
    this.id = 0;
  }

  private searchSongs() {
    this.dataService
      .getSongsById(this.id)
      .subscribe(songs => (this.songs = songs));
  }

  onSubmit() {
    this.searchSongs();
  }
}
