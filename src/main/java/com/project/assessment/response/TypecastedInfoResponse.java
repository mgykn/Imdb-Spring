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
public class TypecastedInfoResponse extends BaseResponse{
	
	
	@JsonProperty("isTypecasted")
	private boolean isTypecasted;

	
	@JsonProperty("typecastedGenres")
	private List<String> typecastedGenres;
	
	public void setResponse(boolean isTypecasted,List<String> typecastedGenres) {
		this.isTypecasted = isTypecasted;
		this.typecastedGenres = typecastedGenres;
		setSuccess("SUCCESS");
		
	}

}
