package com.project.assessment.model;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "imdb_title_akas")
public class TitleAkas {

	
	@Field("titleId")
	@JsonProperty("titleId")
	private String titleId;

	@Field("ordering")
	@JsonProperty("ordering")
	private int ordering;

	@Field("title")
	@JsonProperty("title")
	private String title;

	@Field("region")
	@JsonProperty("region")
	private String region;

	@Field("language")
	@JsonProperty("language")
	private String language;

	@Field("types")
	@JsonProperty("types")
	private List<String> types ;	
	
	@Field("attributes")
	@JsonProperty("attributes")
	private List<String> attributes ;	
	
	@Field("isOriginalTitle")
	@JsonProperty("isOriginalTitle")
	private boolean isOriginalTitle ;
	
	
	
}
