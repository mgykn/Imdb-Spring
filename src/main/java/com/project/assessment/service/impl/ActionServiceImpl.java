package com.project.assessment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assessment.model.NameBasics;
import com.project.assessment.model.TitleBasics;
import com.project.assessment.model.TitlePrincipals;
import com.project.assessment.repository.NameBasicsRepository;
import com.project.assessment.repository.TitleBasicsRepository;
import com.project.assessment.repository.TitlePrincipalsRepository;
import com.project.assessment.request.ActorRequest;
import com.project.assessment.response.CoincidenceInfoResponse;
import com.project.assessment.response.DegreeInfoResponse;
import com.project.assessment.response.TypecastedInfoResponse;
import com.project.assessment.service.ActionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private NameBasicsRepository nameBasicsRepository;

	@Autowired
	private TitleBasicsRepository titleBasicsRepository;

	@Autowired
	private TitlePrincipalsRepository titlePrincipalsRepository;

	@Override
	public TypecastedInfoResponse typeCastProcess(ActorRequest actorRequest) {

		boolean isTypecasted = false;
		TypecastedInfoResponse response = new TypecastedInfoResponse();

		log.info("Getting Requested Actors infos by Actor Name: {}", actorRequest.getActorName());
		List<NameBasics> nameBasics = nameBasicsRepository.findByPrimaryName(actorRequest.getActorName());
		log.info("Requested Actors infos : {}", nameBasics);

		log.info("Getting Requested Actors Movies details by movie ids: {}", nameBasics.get(0).getKnownForTitles());
		List<TitleBasics> titleBasics = titleBasicsRepository.findByTconstIn(nameBasics.get(0).getKnownForTitles());
		log.info("Actors Movies details : {}", titleBasics);

		log.info("Creating value to keep actors movie size");
		float count = titleBasics.size();
		List<String> list = new ArrayList<>();

		log.info("Collecting actors movie genres in List");

		for (TitleBasics titleBasic : titleBasics) {
			for (String t : titleBasic.getGenres()) {
				list.add(t);
			}
			log.info("Collected List:{}", list);
		}
		Set<String> unique = new HashSet<>(list);
		log.info("HashSet:{}", unique);
		List<String> typecastedType = new ArrayList<>();

		for (String key : unique) {
			float x = Collections.frequency(list, key);
			log.info("Count is {} for this value {}", x, key);
			if (x >= (float) Math.ceil(count / 2)) {
				log.info("count is {} for this value {}", x, key);
				typecastedType.add(key);

			}
			log.info("count is {} for this value {}", x, key);
		}
		if (!typecastedType.isEmpty()) {
			isTypecasted = true;
		}
		response.setResponse(isTypecasted, typecastedType);
		return response;
	}

	@Override
	public CoincidenceInfoResponse getCoincidence(String firstActorName, String secondActorName) {

		CoincidenceInfoResponse coincidenceInfoResponse = new CoincidenceInfoResponse();
		List<String> coincidenceMovieNames = new ArrayList<>();
		List<String> actorsNamelist = new ArrayList<>();
		actorsNamelist.add(firstActorName);
		actorsNamelist.add(secondActorName);
		log.info("Getting Requested Actors Movies by Actor's Names: {}", actorsNamelist);
		List<NameBasics> nameBasicsInfos = nameBasicsRepository.findByPrimaryNameIn(actorsNamelist);
		log.info("Add movieId in a list if there is same one");

		List<String> resultsViaLoop = createSharedListWithSameParams(nameBasicsInfos.get(0).getKnownForTitles(),
				nameBasicsInfos.get(1).getKnownForTitles());
		for (String obj : resultsViaLoop) {
			log.info("Getting Same Movie Name in Db with:{}", obj);
			TitleBasics titleBasics = titleBasicsRepository.findByTconst(obj);
			coincidenceMovieNames.add(titleBasics.getPrimaryTitle());
		}
		coincidenceInfoResponse.setResponse(coincidenceMovieNames, "SUCCESS");
		return coincidenceInfoResponse;
	}

	@Override
	public DegreeInfoResponse calculateDegree(ActorRequest actorRequest) {

		DegreeInfoResponse degreeInfoResponse = new DegreeInfoResponse();
		String kevinFullName = "Kevin Bacon";
		log.info("Getting Requested Actors info via Actor Name: {}", actorRequest.getActorName());

		List<NameBasics> actors = nameBasicsRepository.findByPrimaryName(actorRequest.getActorName());
		log.info("Getting Kevins info via Actor Name: {}", kevinFullName);
		NameBasics kevinBacon = nameBasicsRepository.findByNconst("nm0000102");

		List<String> listOfActorsIds = new ArrayList<>();
		List<String> listOfMoviesIds = new ArrayList<>();

		for (NameBasics actorInfo : actors) {
			listOfActorsIds.add(actorInfo.getNconst());
		}

		for (int degree = 0; degree < 7; degree++) {
			List<NameBasics> nameBasicsInfos = nameBasicsRepository.findByNconstIn(listOfActorsIds);
			for (NameBasics actorsInfo : nameBasicsInfos) {
				log.info("Check There is any match Kevins Movies with Founded Actors Movies : {}",
						actorsInfo.getPrimaryName());
				List<String> resultsViaLoop = createSharedListWithSameParams(actorsInfo.getKnownForTitles(),
						kevinBacon.getKnownForTitles());

				if (!resultsViaLoop.isEmpty()) {
					degreeInfoResponse.setDegreeNumber(degree);
					return degreeInfoResponse;
				}
				listOfMoviesIds.addAll(actorsInfo.getKnownForTitles());
			}
			List<TitlePrincipals> enteredActorsFilmsActors = titlePrincipalsRepository
					.findByTconstInAndCategory(listOfMoviesIds, "actor");

			listOfActorsIds.clear();
			for (TitlePrincipals enteredActorsFilmsActor : enteredActorsFilmsActors) {
				listOfActorsIds.add(enteredActorsFilmsActor.getNconst());
			}
		}

		return null;

	}

	private static List<String> createSharedListWithSameParams(List<String> listOne, List<String> listTwo) {
		List<String> listOneList = listOne.stream().filter(two -> listTwo.stream().anyMatch(one -> one.equals(two)))
				.collect(Collectors.toList());
		return listOneList;
	}

}
