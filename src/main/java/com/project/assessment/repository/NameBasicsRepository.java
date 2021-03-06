package com.project.assessment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.assessment.model.NameBasics;

@Repository
public interface NameBasicsRepository extends MongoRepository<NameBasics, String> {

	List<NameBasics> findByNconstIn(List<String> list);

	NameBasics findByNconst(String string);

	List<NameBasics> findByKnownForTitlesIn(List<String> listOfTitles);

	List<NameBasics> findByPrimaryName(String list);

	List<NameBasics> findByPrimaryNameIn(List<String> list);

}
