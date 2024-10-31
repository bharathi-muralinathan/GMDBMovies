package com.gmdb.gmdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    public MovieEntity findByTitle(String title);

}
