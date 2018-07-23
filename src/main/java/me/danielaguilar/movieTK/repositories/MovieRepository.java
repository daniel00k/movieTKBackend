package me.danielaguilar.movieTK.repositories;

import me.danielaguilar.movieTK.models.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    //@Query("select title, description from movie order by id desc")
    Movie findTopByOrderByIdDesc();
}
