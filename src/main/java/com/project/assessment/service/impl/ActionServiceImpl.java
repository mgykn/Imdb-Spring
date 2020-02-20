package com.project.assessment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
	public TypecastedInfoResponse typeCastProcess(String actorName) {

		TypecastedInfoResponse response = new TypecastedInfoResponse();
		NameBasics nameBasics = nameBasicsRepository.findByNconst("nm0000002");
		log.info("namebasics : {}", nameBasics);
		List<TitleBasics> titleBasics = titleBasicsRepository.findByTconstIn(nameBasics.getKnownForTitles());
		log.info("titleBasics : {}", titleBasics);

		float count = titleBasics.size();
		List<String> list = new ArrayList<String>();

		for (TitleBasics titleBasic : titleBasics) {
			for (String t : titleBasic.getGenres()) {
				list.add(t);
			}
			log.info("msg:{}", list);
		}
		Set<String> unique = new HashSet<String>(list);
		for (String key : unique) {
			System.out.println(key + ": " + Collections.frequency(list, key));
			float x = Collections.frequency(list, key);
			System.out.println(key + " x: " + x);
			if (x >= (float) Math.ceil(count / 2)) {
				System.out.println(" typecast: ");

			}
		}

		return response;
	}

	@Override
	public CoincidenceInfoResponse getCoincidence(String actorName1, String actorName2) {
		CoincidenceInfoResponse coincidenceInfoResponse = new CoincidenceInfoResponse();
		List<String> responselist = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		list.add(actorName1);
		list.add(actorName2);
		List<NameBasics> nameBasicsInfos = nameBasicsRepository.findByNconstIn(list);
		List<String> resultsViaLoop = createSharedListWithSameParamsViaStream(
				nameBasicsInfos.get(0).getKnownForTitles(), nameBasicsInfos.get(1).getKnownForTitles());
		for (String obj : resultsViaLoop) {
			TitleBasics titleBasics = titleBasicsRepository.findByTconst(obj);
			responselist.add(titleBasics.getPrimaryTitle());
		}
		coincidenceInfoResponse.setCoincidenceMovies(responselist);
		return coincidenceInfoResponse;
	}

	@Override
	public DegreeInfoResponse calculateDegree(String actorName) {

		DegreeInfoResponse degreeInfoResponse = new DegreeInfoResponse();
		String kevinId = "nm0000102";
		NameBasics actor = nameBasicsRepository.findByNconst(actorName);
		NameBasics kevinBacon = nameBasicsRepository.findByNconst(kevinId);
		List<String> listOfNconsts = new ArrayList<String>();
		List<String> listOfTconsts = new ArrayList<String>();

		listOfNconsts.add(actor.getNconst());
		listOfTconsts.addAll(kevinBacon.getKnownForTitles());

		for (int degree = 0; degree < 7; degree++) {
			listOfNconsts.addAll(listOfNconsts);
			List<NameBasics> nameBasicsInfos = nameBasicsRepository.findByNconstIn(listOfNconsts);
			for (NameBasics actorsFilmInfo : nameBasicsInfos) {

				List<String> resultsViaLoop = createSharedListWithSameParamsViaStream(
						actorsFilmInfo.getKnownForTitles(), listOfTconsts);

				if (resultsViaLoop.size() != 0) {
					degreeInfoResponse.setDegreeNumber(degree);
					return degreeInfoResponse;
				}
				
				List<TitlePrincipals> enteredActorsFilmsActors = titlePrincipalsRepository
						.findByTconstAndCategoryIn(actorsFilmInfo.getKnownForTitles(), "actor");

				listOfNconsts.clear();
				for (TitlePrincipals enteredActorsFilmsActor : enteredActorsFilmsActors) {
					listOfNconsts.add(enteredActorsFilmsActor.getNconst());
				}

			}
		}

		return null;

	}
	


	private static List<String> createSharedListWithSameParamsViaStream(List<String> listOne, List<String> listTwo) {
		List<String> listOneList = listOne.stream().filter(two -> listTwo.stream().anyMatch(one -> one.equals(two)))
				.collect(Collectors.toList());
		return listOneList;
	}

}
