package me.danielaguilar.movieTK.repositories;

import me.danielaguilar.movieTK.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
