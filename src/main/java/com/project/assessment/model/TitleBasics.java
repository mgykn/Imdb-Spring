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
@Document(collection = "imdb_title_basics")
public class TitleBasics {
		
	@Field("tconst")
	@JsonProperty("tconst")
	@Indexed(unique = true)
	private String tconst;

	@Field("titleType")
	@JsonProperty("titleType")
	private String titleType;

	@Field("primaryTitle")
	@JsonProperty("primaryTitle")
	private String primaryTitle;
	
	@Field("originalTitle")
	@JsonProperty("originalTitle")
	private String originalTitle;
	
	@Field("isAdult")
	@JsonProperty("isAdult")
	private int isAdult;
	
	@Field("startYear")
	@JsonProperty("startYear")
	private String startYear;
	
	@Field("endYear")
	@JsonProperty("endYear")
	private String endYear;
	
	@Field("runtimeMinutes")
	@JsonProperty("runtimeMinutes")
	private List<String> runtimeMinutes;
	
	@Field("genres")
	@JsonProperty("genres")
	private List<String> genres;	
	

}
