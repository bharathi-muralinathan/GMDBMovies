package com.gmdb.gmdb;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Getter
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String director;
    private String actors;
    private String releaseYear;
    private String description;
    private Double starRating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserReviewEntity> userReview;

    public MovieEntity(String title, String director, String actors, String releaseYear, String description, Double starRating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.description = description;
        this.starRating = starRating;
        this.userReview = new ArrayList<UserReviewEntity>();
    }

    public MovieEntity() {
    }

    public MovieEntity(Long id, String title, String director, String actors, String releaseYear, String description, Double starRating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.description = description;
        this.starRating = starRating;
        this.userReview = new ArrayList<UserReviewEntity>();
    }

}
