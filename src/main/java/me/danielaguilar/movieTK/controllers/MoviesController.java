package me.danielaguilar.movieTK.controllers;

import me.danielaguilar.movieTK.models.Movie;
import me.danielaguilar.movieTK.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Movie> show(@PathVariable("id") Long id){
        Optional<Movie> movieOptional = repository.findById(id);
        if(movieOptional.isPresent()){
            return new ResponseEntity<Movie>(movieOptional.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Movie> create(@RequestBody Movie movie){
        repository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> update(@PathVariable("id") Long id, @RequestBody Movie movie){
        Optional<Movie> currentMovie = repository.findById(id);
        if(currentMovie.isPresent()){
            movie.setId(id);
            repository.save(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            Optional<Movie> movieOptional = repository.findById(id);
            if(!movieOptional.isPresent()){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
