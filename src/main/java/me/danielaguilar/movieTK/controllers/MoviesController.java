package me.danielaguilar.movieTK.controllers;

import me.danielaguilar.movieTK.models.Movie;
import me.danielaguilar.movieTK.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MoviesController {
    private MovieRepository repository;

    public MoviesController(MovieRepository repository){
        this.repository = repository;
    }

    @GetMapping("/movies")
    public Iterable<Movie> index(){
        return repository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie show(@PathVariable("id") Long id){
        Optional<Movie> movieOptional = repository.findById(id);
        if(movieOptional.isPresent()){
            return movieOptional.get();
        }else {
            return new Movie();
        }
    }

    /*
    *@RequestBody
    {
        "title": "Bilbo",
        "description": "Baggins"
    }
*/
    @PostMapping("/movies")
    public Movie create(@RequestBody Movie movie){
        repository.save(movie);
        return movie;
    }

    @PutMapping("/movies/{id}")
    public Movie update(@PathVariable("id") Long id, @RequestBody Movie movie){
        Optional<Movie> currentMovie = repository.findById(id);
        if(currentMovie.isPresent()){
            movie.setId(id);
            repository.save(movie);
        }else {
             ResponseEntity.notFound().build();
        }
        return movie;
    }

    @DeleteMapping("/movies/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        repository.deleteById(id);
        Optional<Movie> movieOptional = repository.findById(id);
        if(!movieOptional.isPresent()){
            return true;

        }else {
            return false;
        }
    }
}
