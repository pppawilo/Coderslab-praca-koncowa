import { Component, OnInit, Input } from "@angular/core";
import { SongService } from "../song.service";
import { Song } from "../song";
import { SongsListComponent } from "../songs-list/songs-list.component";

@Component({
  selector: "app-song-details",
  templateUrl: "./song-details.component.html",
  styleUrls: ["./song-details.component.css"]
})
export class SongDetailsComponent implements OnInit {
  @Input() song: Song;

  constructor(
    private songService: SongService,
    private listComponent: SongsListComponent
  ) {}

  ngOnInit() {}

  updateActive(isActive: boolean) {
    this.songService
      .updateSong(this.song.id, {
        title: this.song.title,
        author: this.song.author,
        chords: this.song.chords,
        lyrics: this.song.lyrics
      })
      .subscribe(
        data => {
          console.log(data);
          this.song = data as Song;
        },
        error => console.log(error)
      );
  }

  deleteSong() {
    this.songService.deleteSong(this.song.id).subscribe(
      data => {
        console.log(data);
        this.listComponent.reloadData();
      },
      error => console.log(error)
    );
  }
}
