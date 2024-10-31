package com.gmdb.gmdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer starRating;
    private String review;


    public UserReviewEntity(Integer starRating, String review) {
        this.starRating = starRating;
        this.review = review;
    }
}
