package com.gmdb.gmdb;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    MovieRepository repository;

    public MovieService(MovieRepository repository){
        this.repository = repository;
    }

    public List<MovieDTO> getAllMovies() {
        return repository.findAll()
                .stream()
                .map(this::mapToMovies)
                .collect(Collectors.toList());
    }


    public MovieDTO getMovieByTitle(String title) throws MovieNotFoundException {
        MovieEntity movie = repository.findByTitle(title);
        if(movie == null){
            throw new MovieNotFoundException("Movie Doesn't Exist");
        }
        return mapToMovies(movie);
    }


    private MovieDTO mapToMovies(MovieEntity movieEntity) {
         List<UserReviewDTO> userReviewDTOS = new ArrayList<>();
         for(UserReviewEntity review : movieEntity.getUserReview()){
             userReviewDTOS.add(new UserReviewDTO(review.getStarRating(),
                     review.getReview()));
         }

        MovieDTO movie =  new MovieDTO(
                movieEntity.getId(),
                movieEntity.getTitle(),
                movieEntity.getDirector(),
                movieEntity.getActors(),
                movieEntity.getReleaseYear(),
                movieEntity.getDescription(),
                movieEntity.getStarRating(),
                 userReviewDTOS);

        return movie;

    }

    public MovieDTO acceptStarRating(String title, UserReviewDTO userReviewDTO) {
        if(userReviewDTO.getStarRating() == 0){
            throw new StarRatingRequiredException("Please submit Star Rating!!");
        }
        MovieEntity movie = repository.findByTitle(title);

        if(movie != null) {
            List<Integer> rating = movie.getUserReview()
                    .stream()
                    .map(val -> val.getStarRating())
                    .collect(Collectors.toList());
            movie.getUserReview().add(new UserReviewEntity(userReviewDTO.getStarRating(),
                    userReviewDTO.getReview()));
            if(!movie.getUserReview().isEmpty()){
                rating.add(userReviewDTO.getStarRating());
                double averageRating = rating.stream()
                        .mapToDouble(val -> val)
                        .average().getAsDouble();
                movie.setStarRating(averageRating);

            }
            movie = repository.save(movie);
            return mapToMovies(movie);
        }
        return null;
    }
}
