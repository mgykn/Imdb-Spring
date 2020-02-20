package com.project.assessment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.assessment.model.TitleAkas;

@Repository
public interface TitleAkasRepository extends MongoRepository<TitleAkas, String> {

	
	
}
