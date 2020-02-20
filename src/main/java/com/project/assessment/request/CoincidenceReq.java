package com.project.assessment.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class CoincidenceReq {

		@JsonProperty("actorName1")
		@ApiModelProperty(required = false)
		    private String actorName1;
		
		@JsonProperty("actorName2")
		@ApiModelProperty(required = false)
		    private String actorName2;
		
		
	

}
