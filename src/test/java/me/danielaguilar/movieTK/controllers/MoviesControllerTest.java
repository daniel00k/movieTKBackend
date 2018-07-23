package me.danielaguilar.movieTK.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.danielaguilar.movieTK.models.Movie;
import me.danielaguilar.movieTK.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTest {

    final ObjectMapper mapper = new ObjectMapper();
    Movie interstellar = new Movie(1L, "interstellar", "lalala al aalaal");
    Movie pulp = new Movie(2L, "pulp fiction", "mmammama am am a amaam");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MovieRepository repository;

    @Before
    public void setUp(){
        repository.deleteAll();
    }

    @Test
    public void shouldReturnTheListOfMovies() throws Exception {
        repository.save(interstellar);
        repository.save(pulp);
        this.mockMvc.perform(get("/movies")).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(repository.findAll())));
    }

    @Test
    public void shouldReturnTheMovieWithGivenId() throws Exception {
        Movie savedMovie = repository.save(interstellar);
        this.mockMvc.perform(get("/movies/"+savedMovie.getId())).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(savedMovie)));

        this.mockMvc.perform(get("/movies/-1")).andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturnTheCreatedMovie() throws Exception {

        Movie zombieland = new Movie("zombieland", "mucho chocolate");
        this.mockMvc.perform(post("/movies").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(zombieland)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(repository.findTopByOrderByIdDesc())));
    }

    @Test
    public void shouldReturnTheUpdatedMovie() throws Exception {
        Movie savedMovie = repository.save(interstellar);
        Movie zombieland = new Movie("zombieland", "caleta de chocolate");
        Movie updatedMovie = new Movie(savedMovie.getId(), "zombieland", "caleta de chocolate");
        this.mockMvc.perform(put("/movies/"+savedMovie.getId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(zombieland)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(updatedMovie)));

        this.mockMvc.perform(put("/movies/-1").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(zombieland)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnTheStateForDeletion() throws Exception {
        Movie savedMovie = repository.save(interstellar);
        this.mockMvc.perform(delete("/movies/"+savedMovie.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/movies/-1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
