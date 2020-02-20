package com.project.assessment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.assessment.model.TitlePrincipals;


@Repository
public interface TitlePrincipalsRepository extends MongoRepository<TitlePrincipals, String> {

	List<TitlePrincipals> findByTconstInAndCategory(List<String> knownForTitles, String string);


	
	
}
