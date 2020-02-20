package com.project.assessment.service;

import com.project.assessment.request.ActorRequest;
import com.project.assessment.response.CoincidenceInfoResponse;
import com.project.assessment.response.DegreeInfoResponse;
import com.project.assessment.response.TypecastedInfoResponse;

public interface ActionService {

	TypecastedInfoResponse typeCastProcess(ActorRequest actorRequest);

	CoincidenceInfoResponse getCoincidence(String actorName1, String actorName2);

	DegreeInfoResponse calculateDegree(ActorRequest actorRequest);

}
