package com.project.assessment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.assessment.model.TitleCrew;


@Repository
public interface TitleCrewRepository extends MongoRepository<TitleCrew, String> {

	
	
}
