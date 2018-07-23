package me.danielaguilar.movieTK.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.danielaguilar.movieTK.repositories.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MovieRepository repository;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(get("/movies")).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(repository.findAll())));
    }

    /*
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
        repository.deleteById(id);
        Optional<Movie> movieOptional = repository.findById(id);
        if(!movieOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }*/
}
