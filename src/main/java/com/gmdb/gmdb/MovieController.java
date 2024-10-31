package com.gmdb.gmdb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    MovieService service;

    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping("/movies")
    public List<MovieDTO> getAllMovies(){
        return service.getAllMovies();
    }

    @GetMapping("/movies/{title}")
    public MovieDTO getMovieByTitle(@PathVariable String title) {
        return service.getMovieByTitle(title);
    }

    @PostMapping("/movies/{title}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MovieDTO reviewMovie(@PathVariable String title,
                                @RequestBody UserReviewDTO userReviewDTO) {

        return service.acceptStarRating(title, userReviewDTO);
    }

}
