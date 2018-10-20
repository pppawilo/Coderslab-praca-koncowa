package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
