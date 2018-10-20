import { Component, OnInit } from "@angular/core";
import { SongService } from "../song.service";
import { Song } from "../song";

@Component({
  selector: "app-create-song",
  templateUrl: "./create-song.component.html",
  styleUrls: ["./create-song.component.css"]
})
export class CreateSongComponent implements OnInit {
  song: Song = new Song();
  submitted = false;

  constructor(private songService: SongService) {}

  ngOnInit() {}

  newSong(): void {
    this.submitted = false;
    this.song = new Song();
  }

  save() {
    this.songService
      .createSong(this.song)
      .subscribe(data => console.log(data), error => console.log(error));
    this.song = new Song();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
