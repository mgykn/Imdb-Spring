package com.project.assessment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class CoincidenceInfoResponse extends BaseResponse{
	
	@JsonProperty("coincidenceMovies")
	private List<String> coincidenceMovies;
	
	public void setResponse(List<String> coincidenceMovies,String msg) {
		this.coincidenceMovies = coincidenceMovies;
		setSuccess(msg);
		
	}

}
