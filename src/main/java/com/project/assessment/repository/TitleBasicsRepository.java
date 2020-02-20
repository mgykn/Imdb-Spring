package com.project.assessment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.assessment.model.TitleBasics;


@Repository
public interface TitleBasicsRepository extends MongoRepository<TitleBasics, String> {


	
	List<TitleBasics> findByTconstIn(List<String> tconst);                // 1. Spring JPA In cause using method name

//    @Query("SELECT t FROM TitleBasics t WHERE e.tconst IN (:tconst)")     // 2. Spring JPA In cause using @Query
//    List<TitleBasics> findByTconst(@Param("tconst")List<String> tconst);
//
	// @Query("SELECT t FROM imdb_title_basics t WHERE t.IN (:names)")  
	TitleBasics findByTconst(String title);

	
	
}
