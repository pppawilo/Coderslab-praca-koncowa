package pl.coderslab.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.domain.Song;
import pl.coderslab.repository.SongRepository;

@CrossOrigin(origins = { "http://localhost:4200", "http://127.0.0.1:8081" })
@RestController
public class SongController {

	@Autowired
	SongRepository songRepository;

	@GetMapping("/songs")
	public List<Song> getAllSongs() {
		System.out.println("getAllSongs");

		List<Song> songs = new ArrayList<>();
		songRepository.findAll().forEach(songs::add);

		return songs;
	}

	@PostMapping(value = "/songs")
	public Song postSong(@RequestBody Song song) {
		Song newSong = new Song();
		newSong.setAuthor(song.getAuthor());
		newSong.setTitle(song.getTitle());
		newSong.setChords(song.getChords());
		newSong.setLyrics(song.getLyrics());
		Song resultSong = songRepository.save(newSong);
		return resultSong;
	}

	@DeleteMapping("/songs/{id}")
	public ResponseEntity<String> deleteSong(@PathVariable("id") long id) {
		System.out.println("delete song - id:" + id);

		songRepository.deleteById(id);

		return new ResponseEntity<>("Song deleted.", HttpStatus.OK);
	}

	@DeleteMapping("/songs/delete")
	public ResponseEntity<String> deleteAllSongs() {
		System.out.println("delete all songs");

		songRepository.deleteAll();

		return new ResponseEntity<>("No more songs.", HttpStatus.OK);
	}

	@GetMapping(value = "/songs/{id}")
	public ResponseEntity<Song> findById(@PathVariable long id) {

		Optional<Song> optionalSong = songRepository.findById(id);
		if (optionalSong.isPresent()) {
			Song resultSong = optionalSong.get();
			return new ResponseEntity<>(resultSong, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/songs/{id}")
	public ResponseEntity<Song> updateSong(@PathVariable("id") long id, @RequestBody Song song) {
		System.out.println("update song - id:" + id);

		Optional<Song> optionalSong = songRepository.findById(id);

		if (optionalSong.isPresent()) {
			Song resultSong = optionalSong.get();
			resultSong.setAuthor(song.getAuthor());
			resultSong.setTitle(song.getTitle());
			resultSong.setChords(song.getChords());
			resultSong.setLyrics(song.getLyrics());
			return new ResponseEntity<>(songRepository.save(resultSong), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}