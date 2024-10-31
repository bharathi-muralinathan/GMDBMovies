package com.gmdb.gmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    @JsonIgnoreProperties
    private Long id;

    private String title;
    private String director;
    private String actors;
    private String releaseYear;
    private String description;
    private Double starRating;
    private List<UserReviewDTO> userReviewDTOS;

    public MovieDTO(String title, String director, String actors, String releaseYear, String description,
                    Double starRating, List<UserReviewDTO> userReviewDTOS) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.description = description;
        this.starRating = starRating;
        this.userReviewDTOS = userReviewDTOS;
    }
}
