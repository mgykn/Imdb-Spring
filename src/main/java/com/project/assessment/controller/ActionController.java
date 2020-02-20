package com.project.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.assessment.request.CoincidenceReq;
import com.project.assessment.request.ActorRequest;
import com.project.assessment.response.CoincidenceInfoResponse;
import com.project.assessment.response.DegreeInfoResponse;
import com.project.assessment.response.TypecastedInfoResponse;
import com.project.assessment.service.ActionService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping(value = "/v1", produces = "application/json", consumes = "application/json")
@RestController
public class ActionController {
	
	@Autowired
	private ActionService actionService;
	
	@ApiOperation(value = "Typecasting", notes = "Check and Determine is it typecasting.")
	@RequestMapping(value = "/typecast", method = RequestMethod.POST)
	public ResponseEntity<TypecastedInfoResponse> checkTypecasting(@RequestBody ActorRequest actorRequest){
		log.info("Starting checkTypecasting process {}",actorRequest);
		
		TypecastedInfoResponse response = this.actionService.typeCastProcess(actorRequest);
		return ResponseEntity.accepted().body(response);
	}
	
	@ApiOperation(value = "Find the coincidence", notes = "Find the coincidence")
	@RequestMapping(value = "/coincidence", method = RequestMethod.POST)
	public ResponseEntity<CoincidenceInfoResponse> findCoincidence(@RequestBody CoincidenceReq req ){
		log.info("Starting findCoincidence process {}",req);
		
		CoincidenceInfoResponse response = this.actionService.getCoincidence(req.getActorName1(),req.getActorName2());
		return ResponseEntity.accepted().body(response);
	}
	
	@ApiOperation(value = " ", notes = "calculate degree of separation between Kevin Bacon")
	@RequestMapping(value = "/degree/calculate", method = RequestMethod.POST)
	public ResponseEntity<DegreeInfoResponse> calculateDegree(@RequestBody ActorRequest actorRequest){
		log.info("Starting calculateDegree process {}", actorRequest);
		DegreeInfoResponse response = this.actionService.calculateDegree(actorRequest);
		return ResponseEntity.accepted().body(response);
	}

}

