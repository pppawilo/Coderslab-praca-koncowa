import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { SongService } from "../song.service";
import { Song } from "../song";

@Component({
  selector: "app-songs-list",
  templateUrl: "./songs-list.component.html",
  styleUrls: ["./songs-list.component.css"]
})
export class SongsListComponent implements OnInit {
  songs: Observable<Song[]>;

  constructor(private songService: SongService) {}

  ngOnInit() {
    this.reloadData();
  }

  deleteSongs() {
    this.songService.deleteAll().subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log("ERROR: " + error)
    );
  }

  reloadData() {
    this.songs = this.songService.getSongsList();
  }
}
