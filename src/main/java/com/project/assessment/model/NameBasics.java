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
@Document(collection = "imdb_name")
public class NameBasics {

	
	@Field("nconst")
	@JsonProperty("nconst")
	@Indexed(unique = true)
	private String nconst;

	@Field("primaryName")
	@JsonProperty("primaryName")
	@Indexed
	private String primaryName;
	
	@Field("birthYear")
	@JsonProperty("birthYear")
	private String birthYear;
	
	@Field("deathYear")
	@JsonProperty("deathYear")
	private String deathYear;
	
	@Field("primaryProfession")
	@JsonProperty("primaryProfession")
	private List<String> primaryProfession ;	
	
	@Field("knownForTitles")
	@JsonProperty("knownForTitles")
	private List<String> knownForTitles;



}
