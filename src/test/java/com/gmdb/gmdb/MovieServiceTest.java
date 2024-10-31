package com.gmdb.gmdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    MovieService movieService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void getAllMovies(){
        when(movieRepository.findAll()).thenReturn(List.of(new MovieEntity("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D)));
        List<MovieDTO> actualMovies = movieService.getAllMovies();
        assertEquals(List.of(new MovieDTO("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D,new ArrayList<>())),actualMovies);
        verify(movieRepository,times(1)).findAll();
    }

    @Test
    public void getMovieByTitle() throws MovieNotFoundException {
        when(movieRepository.findByTitle("The Avengers")).thenReturn(new MovieEntity("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D));
        MovieDTO actualMovie = movieService.getMovieByTitle("The Avengers");
        verify(movieRepository,times(1)).findByTitle("The Avengers");
        assertEquals(new MovieDTO("The Avengers","Joss Whedon",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                ,0D,new ArrayList<>()),actualMovie);

    }

    @Test
    public void getMovieByTitleNotExistent(){
        when(movieRepository.findByTitle("Superman")).thenReturn(null);
        MovieNotFoundException exception = assertThrows(MovieNotFoundException.class,()->{
            movieService.getMovieByTitle("Superman");});
        verify(movieRepository,times(1)).findByTitle("Superman");

        assertEquals("Movie Doesn't Exist", exception.getMessage());
    }

    @Test
    public void acceptStarRating(){
        MovieEntity movieEntity =
                new MovieEntity(18l, "The Avengers","Joss Whedon",
                        "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                        "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                        ,0D);
        when(movieRepository.findByTitle("The Avengers")).thenReturn(movieEntity);

        when(movieRepository.save(movieEntity)).thenReturn(movieEntity);

        UserReviewDTO review1 = new UserReviewDTO(5,"Good Movie");
        UserReviewDTO review2 = new UserReviewDTO(3,"bad Movie");

        MovieDTO movie = movieService.acceptStarRating("The Avengers",review1);
        verify(movieRepository,times(1)).findByTitle("The Avengers");
        assertEquals(5,movie.getStarRating());

        movie = movieService.acceptStarRating("The Avengers",review2);
        verify(movieRepository,times(2)).findByTitle("The Avengers");
        assertEquals(4,movie.getStarRating());
    }


    @Test
    public void acceptUserReview(){
        MovieEntity movieEntity =
                new MovieEntity(18l, "The Avengers","Joss Whedon",
                        "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                        "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity"
                        ,0D);
        when(movieRepository.findByTitle("The Avengers")).thenReturn(movieEntity);

        when(movieRepository.save(movieEntity)).thenReturn(movieEntity);

        UserReviewDTO review1 = new UserReviewDTO(5,"Good Movie");
        UserReviewDTO review2 = new UserReviewDTO(3,"Bad Movie");

        MovieDTO movie = movieService.acceptStarRating("The Avengers",review1);
        verify(movieRepository,times(1)).findByTitle("The Avengers");
        assertEquals(5,movie.getUserReviewDTOS().get(0).getStarRating());
        assertEquals("Good Movie",movie.getUserReviewDTOS().get(0).getReview());

        movie = movieService.acceptStarRating("The Avengers",review2);
        verify(movieRepository,times(2)).findByTitle("The Avengers");
        assertEquals(3,movie.getUserReviewDTOS().get(1).getStarRating());
        assertEquals("Bad Movie",movie.getUserReviewDTOS().get(1).getReview());
    }

    @Test
    public void submitReviewNoRating(){
        StarRatingRequiredException exception = assertThrows(StarRatingRequiredException.class,()->{
            movieService.acceptStarRating("The Avengers",new UserReviewDTO(0,"Bad Movie"));});
        assertEquals("Please submit Star Rating!!", exception.getMessage());
    }
}
