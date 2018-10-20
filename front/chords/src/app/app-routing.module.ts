import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SongsListComponent } from "./songs-list/songs-list.component";
import { CreateSongComponent } from "./create-song/create-song.component";
import { SearchSongsComponent } from "./search-songs/search-songs.component";

const routes: Routes = [
  { path: "", redirectTo: "song", pathMatch: "full" },
  { path: "song", component: SongsListComponent },
  { path: "add", component: CreateSongComponent },
  { path: "findbyid", component: SearchSongsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
