import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class SongService {
  private baseUrl = "http://localhost:8080/songs";

  constructor(private http: HttpClient) {}

  getSong(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createSong(song: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, song);
  }

  updateSong(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteSong(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: "text" });
  }

  getSongsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getSongsById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    if(window.confirm('Are you sure you want to delete all songs?')){
        return this.http.delete(`${this.baseUrl}` + `/delete`, {
      responseType: "text"
      });
    }
  }
}
