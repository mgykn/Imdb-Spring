package com.project.assessment.service;

import com.project.assessment.response.CoincidenceInfoResponse;
import com.project.assessment.response.DegreeInfoResponse;
import com.project.assessment.response.TypecastedInfoResponse;

public interface ActionService {

	TypecastedInfoResponse typeCastProcess(String actorName);

	CoincidenceInfoResponse getCoincidence(String actorName1, String actorName2);

	DegreeInfoResponse calculateDegree(String actorName);

}
