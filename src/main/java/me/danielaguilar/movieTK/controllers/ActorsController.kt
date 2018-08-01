package me.danielaguilar.movieTK.controllers

import me.danielaguilar.movieTK.models.Actor
import me.danielaguilar.movieTK.repositories.ActorRepository
import me.danielaguilar.movieTK.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ActorsController(@Autowired val repository: ActorRepository, @Autowired val movieRepository: MovieRepository) {

    companion object {
        const val ACTORS = "/actors"
    }
    @GetMapping(ACTORS)
    fun index(): Iterable<Actor> = repository.findAll()

    @GetMapping("$ACTORS/{id}")
    fun show(@PathVariable("id") id: Long):ResponseEntity<Actor>{
        if (repository.existsById(id)){
            return ResponseEntity(repository.findById(id).get(), HttpStatus.OK)
        }else{
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("${MoviesController.MOVIES}/{id}")
    fun create(@RequestBody actor: Actor, @PathVariable("id") id: Long): ResponseEntity<Actor>{
        if (movieRepository.existsById(id)){
            actor.movie = movieRepository.findById(id).get()
            repository.save(actor)
            return ResponseEntity(HttpStatus.CREATED)
        }else{
            //Ver como reponder con los errores que ocurrieron al guardar
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}